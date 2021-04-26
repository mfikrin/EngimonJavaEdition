package GUI;

// import src.GameState.GameS;

import java.lang.*;

import java.io.File;
import java.util.concurrent.LinkedBlockingDeque;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

// import jdk.jshell.spi.ExecutionControl.EngineTerminationException;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;

// External Import, moved later
import java.util.*;

import Entity.Position;
import Entity.SkillItem;
import Entity.Battle;
import Entity.Map;
import Entity.Player;
import Entity.Engimon.*;
import Entity.Engimon.Fire.*;
import Entity.Engimon.Electric.*;
import Entity.Engimon.FireElectric.*;
import Entity.Engimon.Ground.*;
import Entity.Engimon.Ice.*;
import Entity.Engimon.Water.*;
import Entity.Engimon.WaterGround.*;
import Entity.Engimon.WaterIce.*;
import Entity.SkillItem;
import Entity.Skill.Skill;
import Exception.ElementNotSuitableException;
import Exception.InsufficientLevelException;
// import src.Entity.Position;
import Exception.InventoryFullException;
import Exception.SkillFullException;
import Generator.EngimonGenerator;
import Generator.SkillGenerator;

// -------

public class GamePanel extends JPanel implements ActionListener {

    private Map map = new Map();
    // ----------------------------------- //

    private static final int TILE_SIZE = 40;
    private static final int SCREEN_WIDTH = 15 * TILE_SIZE;
    private static final int SCREEN_HEIGHT = 15 * TILE_SIZE;
    private static final Color BG_COLOR = Color.WHITE;

    // ----------------------------------- //
    private final int STATE_GAME_STOP = -1;
    private final int STATE_PAUSE = 0;
    private final int STATE_MAIN_MENU = 1;
    private final int STATE_EXPLORE_WORLD = 2;
    private final int STATE_BATTLE = 3;
    private final int STATE_BREED = 4;

    private int current_state = STATE_MAIN_MENU;
    private int massage_battle = 1;

    // FLAGS |=~
    private boolean flag_message_box;
    private boolean flag_inventory;
        private int inv_x = 0;
        private int inv_y = 0;
        private boolean inv_mark_engimon = true;
        private int inv_page = 1;
        private int inv_max_page = 5;
        private String inv_status = "";
        private ArrayList<Integer> arr_to_breed = new ArrayList<>();
        private boolean ready_breed = false;

    // ----------------------------------- //

    private boolean battle_ready = false;

    // =================================== //
    // gui components
    private MessageBox message_box;
    // ---

    private int player_x;
    private int player_y;

    private int active_engimon_x;
    private int active_engimon_y;
    private String active_engimon_type;

    private int landscape[][];

    private Queue<Engimon> list_engimon_enemy;

    // --temp
    private final int max_step_count = 5;
    private int step_count = 0;
    // -------
    // =================================== //

    // tester atributes, disposed later
    private static int count_i = 0;
    private boolean is_escape = false;

    private Player player = new Player();

    // |-END Test Attributes
    // ============== Function ENGIMON ENEMY===================== //

    // EXTERNAL FUNCTIONs, moved later
    private void spawnEngimonEnemy() {
        int x, y, k;
        Random randomNumbers = new Random();
        k = randomNumbers.nextInt(4);
        Engimon enemy;
        // mountain 1
        if (k == 0) {
            enemy = EngimonGenerator.generate_fire();
            // random position
            do {
                x = randomNumbers.nextInt(15);
                y = randomNumbers.nextInt(15);
            } while (!map.is_mountain(y, x));
        }
        // water 2
        else if (k == 1) {
            enemy = EngimonGenerator.generate_water();
            // random position
            do {
                x = randomNumbers.nextInt(15);
                y = randomNumbers.nextInt(15);
            } while (!map.is_sea(y, x));
        }
        // grassland 3
        else if (k == 2) {
            enemy = EngimonGenerator.generate_ground();
            // random position
            do {
                x = randomNumbers.nextInt(15);
                y = randomNumbers.nextInt(15);
            } while (!map.is_grassland(y, x));
        }
        // ice 4
        else {
            enemy = EngimonGenerator.generate_ice();
            do {
                x = randomNumbers.nextInt(15);
                y = randomNumbers.nextInt(15);
            } while (!map.is_tundra(y, x));
        }
        Position p = new Position(x, y);
        enemy.set_pos(p);
        enemy.set_live(1);
        list_engimon_enemy.add(enemy);

        // --
        try {
            player.add_engimon(enemy);
        } catch (Exception e) {
            // TODO: handle exception
            // e.printStackTrace();
        }
    }

    // END External Functions

    public Boolean isHitWall(Position P) {
        return P.get_x() > 14 || P.get_y() > 14 || P.get_x() < 0 || P.get_y() < 0;
    }

    public Boolean isHitOtherEnemy(Position P) {
        int a = 0;
        for (Engimon e : list_engimon_enemy) {
            if (e.get_pos().get_x() == P.get_x() && e.get_pos().get_y() == P.get_y()) {
                a += 1;
            }
        }
        return a > 1;

    }

    public Boolean isHitPlayer(Position P) {
        return P.get_x() * TILE_SIZE == this.player_x && P.get_y() * TILE_SIZE == this.player_y;
    }

    public Boolean isHitEngimonPlayer(Position P) {
        return P.get_x() * TILE_SIZE == active_engimon_x && P.get_y() * TILE_SIZE == active_engimon_y;
    }

    public Boolean isCompatibleEnemy(Engimon enemy, Position newPosition) {
        if (map.is_mountain(newPosition)) {
            return enemy.is_fire();
        } else if (map.is_sea(newPosition)) {
            return enemy.is_water();
        } else if (map.is_grassland(newPosition)) {
            return (enemy.is_ground() || enemy.is_electric());
        } else if (map.is_tundra(newPosition)) {
            return enemy.is_ice();
        } else {
            return false;
        }
    }

    private void moveEngimonEnemy() {
        for (Engimon e : list_engimon_enemy) {
            int pengulangan = 0;
            Position newPosition;
            Position oldPosition = e.get_pos();
            // jika tidak menabrak tembok
            do {
                Random randomNumbers = new Random();
                int k = randomNumbers.nextInt(4);
                // gerak ke atas
                if (k == 0) {
                    newPosition = new Position(oldPosition.get_x(), oldPosition.get_y() - 1);
                }
                // gerak ke kanan
                else if (k == 1) {
                    newPosition = new Position(oldPosition.get_x() + 1, oldPosition.get_y());
                }
                // gerak ke bawah
                else if (k == 2) {
                    newPosition = new Position(oldPosition.get_x(), oldPosition.get_y() + 1);
                }
                // bergerak ke kiri
                else {
                    newPosition = new Position(oldPosition.get_x() - 1, oldPosition.get_y());
                }
                pengulangan += 1;
            } while ((isHitWall(newPosition) || isHitOtherEnemy(newPosition) || isHitPlayer(newPosition)
                    || isHitEngimonPlayer(newPosition) || !isCompatibleEnemy(e, newPosition)) && pengulangan < 4);
            if (pengulangan >= 4) {
                e.set_pos(oldPosition);
            } else {
                e.set_pos(newPosition);
            }
        }
    }

    // ============================================== //

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(BG_COLOR);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        init_data();

    }

    private void load_map_data() {
        this.landscape = map.get_landscape();
    }

    private void init_data() {
        try {
            player.add_engimon(EngimonGenerator.generate_rand_engimon());
            player.add_engimon(EngimonGenerator.generate_rand_engimon());
            player.add_engimon(EngimonGenerator.generate_rand_engimon());
            player.set_active_engimon(0);
        } catch (InventoryFullException e) {
            System.out.println("Cannot generate active engimon...");
        }

        try {
            player.add_skill_item(new SkillItem(SkillGenerator.generate_random_skill()));
            player.add_skill_item(new SkillItem(SkillGenerator.generate_random_skill()));
            player.add_skill_item(new SkillItem(SkillGenerator.generate_random_skill()));
            player.add_skill_item(new SkillItem(SkillGenerator.generate_random_skill()));
            player.add_skill_item(new SkillItem(SkillGenerator.generate_random_skill()));
            player.add_skill_item(new SkillItem(SkillGenerator.generate_random_skill()));

        } catch (Exception e) {
            // TODO: handle exception
        }

        // to be replaced by data from gamestate
        this.player_x = player.get_pos().get_x() * TILE_SIZE;
        this.player_y = player.get_pos().get_y() * TILE_SIZE;
        this.active_engimon_x = 0 * TILE_SIZE;
        this.active_engimon_y = 0 * TILE_SIZE;
        // ===== menentukan tipe ========
        if (player.get_engimon().is_fire()) {
            this.active_engimon_type = "fire";
        } else if (player.get_engimon().is_water()) {
            this.active_engimon_type = "water";
        } else if (player.get_engimon().is_ground()) {
            this.active_engimon_type = "earth";
        } else if (player.get_engimon().is_ice()) {
            this.active_engimon_type = "ice";
        } else {
            this.active_engimon_type = "electric";
        }
        // ==============================
        this.list_engimon_enemy = new LinkedList<Engimon>();
        this.message_box = new MessageBox();

        // set flag(s)
        this.flag_message_box = false;
        this.flag_inventory = false;
        // END to be...

        load_map_data();

    }

    // Helper functions

    private void change_engimon_handler(){
        int marker_idx = (inv_page-1)*20 + inv_y*10 + inv_x;
        player.set_active_engimon(marker_idx); 
    }

    private void clear_message_box() {
        this.flag_message_box = false;
        message_box.write("", "", "");
    }

    private void show_command_list() {
        this.flag_message_box = true;
        message_box.write("Navigasi: w/a/s/d atau tanda panah", "B: Battle     E: Inventory",
                "I: Interact     C: Check active Engimon");
    }

    private void interact() {
        this.flag_message_box = true;
        message_box.write(player.get_engimon().get_dialogue(), "Aku " + player.get_engimon().get_name(), "");
    }

    private void update_state() {
        // -- cek
        if (current_state == STATE_EXPLORE_WORLD) {
            // test_print("check surronding");
            if (check_surrrounding_enemy() != null) {
                battle_ready = true;
            }
            System.out.println(battle_ready);
            if (battle_ready) {
                System.out.println("Battle?");
            }
        }
        // update engimon enemy
        System.out.println(this.list_engimon_enemy.size());
    }

    private Engimon check_surrrounding_enemy() {
        int px = this.player_x / TILE_SIZE;
        int py = this.player_y / TILE_SIZE;
        System.out.println(px);
        System.out.println(py);
        for (Engimon e : this.list_engimon_enemy) {
            int ex = e.get_pos().get_x();
            int ey = e.get_pos().get_y();
            // check west, north, east, south
            if ((px == ex + 1 && py == ey) || (px == ex - 1 && py == ey) || (px == ex && py == ey + 1)
                    || (px == ex && py == ey - 1)) {
                return e;
            }
        }
        return null;
    }

    // ---

    // ============================ //
    // === ENTRY POINT GAMELOOP === //
    // ============================ //

    public void paintComponent(Graphics g) {
        // update state
        // -- cek gamestate, lalu update variabel lokal
        // update_state();

        count_i++;
        // test_print(Integer.toString(count_i));
        if (count_i % 100 == 1) {
            moveEngimonEnemy();
        }
        if (count_i % 2000 == 1) {
            spawnEngimonEnemy();
            test_print("anjay");
            System.out.println(list_engimon_enemy.size());
        }
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        // System.out.println("in paint");
        // System.out.println(current_state + 100);
        switch (current_state) {
        case STATE_MAIN_MENU:
            draw_main_menu(g2d);
            break;
        case STATE_EXPLORE_WORLD:
            // System.out.println("should draw maps");
            draw_explore_world(g2d);
            break;
        case STATE_BATTLE:
            if (massage_battle == 2) {
                printMassageBattle2(g2d);
            } else if (massage_battle == 3) {
                draw_battle(g2d);
                printMassageBattle3(g2d);
            } else if (massage_battle == 4) {
                draw_battle(g2d);
                printMassageBattle4(g2d);
            } else {
                draw_battle(g2d);
                printMassageBattle1(g2d);
            }
            break;

        // ...
        default:
            // System.out.println("thrown at blank");
            draw_blank_screen();
            break;
        }

    }
    // --experiment

    public void draw_blank_screen() {
        System.out.println("Blank");
    }
    // -----

    private void draw_message_box(Graphics2D g2d) {
        System.out.println("...........draw mbox");
        Image bg_m_box = new ImageIcon("./images/background.png").getImage();
        g2d.drawImage(bg_m_box, 0 * TILE_SIZE, 12 * TILE_SIZE, SCREEN_WIDTH, 3 * TILE_SIZE, this);

        int font_size = 16;
        Font font = new Font("Serif", Font.PLAIN, font_size);
        g2d.setFont(font);
        g2d.drawString(message_box.get_l1(), TILE_SIZE / 2, 13 * TILE_SIZE - TILE_SIZE / 5);
        g2d.drawString(message_box.get_l2(), TILE_SIZE / 2, 14 * TILE_SIZE - TILE_SIZE / 5 - 8);
        g2d.drawString(message_box.get_l3(), TILE_SIZE / 2, 15 * TILE_SIZE - TILE_SIZE / 5 - 16);

    }

    public void draw_battle(Graphics2D g2d) {
        // this.flag_message_box = true;
        System.out.println("Battle");
        // Image p = new
        // ImageIcon("./images/transparent/engimon_electric.gif").getImage();
        // Image e = new ImageIcon("./images/transparent/engimon_earth.gif").getImage();

        // Image bg = new ImageIcon("./images/battle/bg_3_lives.png").getImage();
        // ambil engimon player
        Image p;
        if (player.get_engimon().is_electric()) {
            p = new ImageIcon("./images/transparent/engimon_electric.gif").getImage();
        } else if (player.get_engimon().is_fire()) {
            p = new ImageIcon("./images/transparent/engimon_fire.gif").getImage();
        } else if (player.get_engimon().is_ground()) {
            p = new ImageIcon("./images/transparent/engimon_Earth.gif").getImage();
        } else if (player.get_engimon().is_ice()) {
            p = new ImageIcon("./images/transparent/engimon_ice.gif").getImage();
        } else {
            p = new ImageIcon("./images/transparent/engimon_water.gif").getImage();
        }
        Image e;
        if (check_surrrounding_enemy().is_electric()) {
            e = new ImageIcon("./images/transparent/engimon_electric.gif").getImage();
        } else if (check_surrrounding_enemy().is_fire()) {
            e = new ImageIcon("./images/transparent/engimon_fire.gif").getImage();
        } else if (check_surrrounding_enemy().is_ground()) {
            e = new ImageIcon("./images/transparent/engimon_Earth.gif").getImage();
        } else if (check_surrrounding_enemy().is_ice()) {
            e = new ImageIcon("./images/transparent/engimon_ice.gif").getImage();
        } else {
            e = new ImageIcon("./images/transparent/engimon_water.gif").getImage();
        }
        Image bg = new ImageIcon("./images/battle/bg_" + player.get_engimon().get_live() + "_lives.png").getImage();
        g2d.drawImage(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
        g2d.drawImage(p, 2 * TILE_SIZE, 7 * TILE_SIZE, 4 * TILE_SIZE, 4 * TILE_SIZE, this);
        g2d.drawImage(e, 11 * TILE_SIZE, 5 * TILE_SIZE, 2 * TILE_SIZE, 2 * TILE_SIZE, this);

        // printMassageBattle1(g2d);
        // if (flag_message_box == true) {
        // draw_message_box(g2d);
        // }

    }

    public void printMassageBattle1(Graphics2D g2d) {
        Battle battle = new Battle(player.get_engimon(), check_surrrounding_enemy());
        String spasi = "                                           ";
        String string1 = spasi + spasi + "POWER PLAYER: " + Double.toString(battle.get_power_level(1))
                + ", POWER ENGIMON ENEMY: " + Double.toString(battle.get_power_level(2));
        String string2 = "";
        for (Engimon e : player.get_inventory_engimon().get_list()) {
            string2 = string2 + e.get_name() + "  ";
        }
        String string3 = "PRESS F FOR FIGHT, ESCAPE FOR BACK TO RXPLORE WORLD, S FOR SWITCH ENGIMON PLAYER ";

        message_box.write(string1, string2, string3);
        g2d.drawString(message_box.get_l1(), TILE_SIZE / 2, 13 * TILE_SIZE - TILE_SIZE / 9);
        g2d.drawString(message_box.get_l2(), TILE_SIZE / 2, 14 * TILE_SIZE - TILE_SIZE / 5 - 12);
        g2d.drawString(message_box.get_l3(), TILE_SIZE / 2, 15 * TILE_SIZE - TILE_SIZE / 5 - 20);

    }

    public void printMassageBattle2(Graphics2D g2d) {
        String string1 = "YOU WIN";
        String string2 = "";
        String string3 = "PRESS ESCAPE FOR BACK TO EXPLORE WORLD";

        message_box.write(string1, string2, string3);
        g2d.drawString(message_box.get_l1(), TILE_SIZE / 2, 13 * TILE_SIZE - TILE_SIZE / 9);
        g2d.drawString(message_box.get_l2(), TILE_SIZE / 2, 14 * TILE_SIZE - TILE_SIZE / 5 - 12);
        g2d.drawString(message_box.get_l3(), TILE_SIZE / 2, 15 * TILE_SIZE - TILE_SIZE / 5 - 20);
    }

    public void printMassageBattle3(Graphics2D g2d) {
        String string1 = "YOU LOSE";
        String string2 = "";
        String string3 = "PRESS B FOR BATTLE AGAIN OR ESCAPE FOR BACK TO EXPLORE WORLD";

        message_box.write(string1, string2, string3);
        g2d.drawString(message_box.get_l1(), TILE_SIZE / 2, 13 * TILE_SIZE - TILE_SIZE / 9);
        g2d.drawString(message_box.get_l2(), TILE_SIZE / 2, 14 * TILE_SIZE - TILE_SIZE / 5 - 12);
        g2d.drawString(message_box.get_l3(), TILE_SIZE / 2, 15 * TILE_SIZE - TILE_SIZE / 5 - 20);
    }

    public void printMassageBattle4(Graphics2D g2d) {
        String string1 = "YOU DRAW";
        String string2 = "";
        String string3 = "PRESS B FOR BATTLE AGAIN OR ESCAPE FOR BACK TO EXPLORE WORLD";

        message_box.write(string1, string2, string3);
        g2d.drawString(message_box.get_l1(), TILE_SIZE / 2, 13 * TILE_SIZE - TILE_SIZE / 9);
        g2d.drawString(message_box.get_l2(), TILE_SIZE / 2, 14 * TILE_SIZE - TILE_SIZE / 5 - 12);
        g2d.drawString(message_box.get_l3(), TILE_SIZE / 2, 15 * TILE_SIZE - TILE_SIZE / 5 - 20);
    }

    public void fightEnemy() {
        Battle battle = new Battle(player.get_engimon(), check_surrrounding_enemy());
        Double x, y;
        Random randomNumbers = new Random();
        x = randomNumbers.nextDouble();
        y = randomNumbers.nextDouble();
        int live = player.get_engimon().get_live();
        if (battle.get_power_level(1) * x > battle.get_power_level(2) * y) {
            massage_battle = 2;
            list_engimon_enemy.remove(check_surrrounding_enemy());
        } else if (battle.get_power_level(1) * x < battle.get_power_level(2) * y) {
            massage_battle = 3;
            player.get_engimon().set_live(live - 1);
        } else {
            massage_battle = 4;
        }
    }

    public void draw_main_menu(Graphics2D g2d) {
        // System.out.println("main menu");
        Image banner = new ImageIcon("./images/title_banner.gif").getImage();
        g2d.drawImage(banner, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
        Image e1 = new ImageIcon("./images/transparent/engimon_fire.gif").getImage();
        Image e2 = new ImageIcon("./images/transparent/engimon_electric.gif").getImage();
        Image e3 = new ImageIcon("./images/transparent/engimon_earth.gif").getImage();
        Image e4 = new ImageIcon("./images/transparent/engimon_ice.gif").getImage();
        Image e5 = new ImageIcon("./images/transparent/engimon_water.gif").getImage();
        Image e6 = new ImageIcon("./images/transparent/engimon_water_ice.gif").getImage();
        Image e7 = new ImageIcon("./images/transparent/engimon_water_earth.gif").getImage();
        Image e8 = new ImageIcon("./images/transparent/engimon_fire_electric.gif").getImage();

        g2d.drawImage(e1, 0 * TILE_SIZE, 14 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, this);
        g2d.drawImage(e2, 2 * TILE_SIZE, 14 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, this);
        g2d.drawImage(e3, 4 * TILE_SIZE, 14 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, this);
        g2d.drawImage(e4, 6 * TILE_SIZE, 14 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, this);
        g2d.drawImage(e5, 8 * TILE_SIZE, 14 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, this);
        g2d.drawImage(e6, 10 * TILE_SIZE, 14 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, this);
        g2d.drawImage(e7, 12 * TILE_SIZE, 14 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, this);
        g2d.drawImage(e8, 14 * TILE_SIZE, 14 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, this);

    }

    public void draw_explore_world(Graphics2D g2d) {
        // System.out.println("in");

        draw_landscape(g2d);
        draw_characters(g2d);
        draw_enemies(g2d);

        if (flag_message_box == true) {
            draw_message_box(g2d);
        } else if (flag_inventory == true) {
            draw_inventory(g2d);
        }
    }

    private void draw_inventory(Graphics2D g2d) {
        Image bg_inv = new ImageIcon("./images/bg_inventory.png").getImage();
        int x_start = 5 * TILE_SIZE / 2;
        g2d.drawImage(bg_inv, x_start, 5 * TILE_SIZE, 10 * TILE_SIZE, 5 * TILE_SIZE, this);
        int er = 0;
        int ec = 0;
        int sr = 0;
        int sc = 0;
        int item_counter = 0;
        int current_page = 1;
        int idx_active = player.get_inventory_engimon().get_index(player.get_engimon());
        for (Engimon ep : player.get_inventory_engimon().get_list()) {
            item_counter += 1;
            if (item_counter > 20) {
                item_counter -= 20;
                current_page += 1;
            }
            if (current_page == inv_page) {
                String element = "";
                if (ep.is_fire() && ep.is_electric()) {
                    element = "fire_electric";
                } else if (ep.is_water() && ep.is_ice()) {
                    element = "water_ice";
                } else if (ep.is_water() && ep.is_ground()) {
                    element = "water_earth";
                } else if (ep.is_fire()) {
                    element = "fire";
                } else if (ep.is_electric()) {
                    element = "electric";
                } else if (ep.is_water()) {
                    element = "water";
                } else if (ep.is_ice()) {
                    element = "ice";
                } else if (ep.is_ground()) {
                    element = "earth";
                }
                ec++;
                if (ec > 10) {
                    ec -= 10;
                    er++;
                }
                if (er > 2) {
                    er -= 2;
                }
                Image ep_img = new ImageIcon("./images/transparent/engimon_" + element + ".gif").getImage();
                g2d.drawImage(ep_img, x_start + (ec - 1) * TILE_SIZE, 13 * TILE_SIZE / 2 + (er - 1) * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE, this);
                System.out.println(ep.get_species());
                
                if (idx_active == item_counter-1){
                    Image e_marker = new ImageIcon("./images/engimon_marker.png").getImage();
                    g2d.drawImage(e_marker, x_start+(ec-1)*TILE_SIZE, 13*TILE_SIZE/2 + (er-1)*TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                
                }
            }

        }

        int si_counter = 0;
        int si_page = 1;
        for (SkillItem si : player.get_inventory_skill_item().get_list()) {
            si_counter += 1;
            if (si_counter > 20) {
                si_counter -= 20;
                si_page += 1;
            }
            if (si_page == inv_page) {
                String element = "";
                if (si.get_skill().is_fire() && si.get_skill().is_electric()) {
                    element = "fire_electric";
                } else if (si.get_skill().is_water() && si.get_skill().is_ice()) {
                    element = "water_ice";
                } else if (si.get_skill().is_water() && si.get_skill().is_ground()) {
                    element = "water_earth";
                } else if (si.get_skill().is_fire()) {
                    element = "fire";
                } else if (si.get_skill().is_electric()) {
                    element = "electric";
                } else if (si.get_skill().is_water()) {
                    element = "water";
                } else if (si.get_skill().is_ice()) {
                    element = "ice";
                } else if (si.get_skill().is_ground()) {
                    element = "earth";
                }
                sc++;
                if (sc > 10) {
                    sc -= 10;
                    sr++;
                }
                if (sr > 2) {
                    sr -= 2;
                }
                System.out.println("#@@@@@@@@@@@@@@@# SI " + element);
                Image si_img = new ImageIcon("./images/skill_item/si_" + element + ".png").getImage();
                g2d.drawImage(si_img, x_start + (sc - 1) * TILE_SIZE, 18 * TILE_SIZE / 2 + (sr - 1) * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE, this);
            }

        }

        Image inv_marker = new ImageIcon("./images/inventory_marker.png").getImage();
        int start_marker_x = x_start;
        int start_marker_y = 11 * TILE_SIZE / 2;
        if (!inv_mark_engimon) {
            start_marker_y += 5 * TILE_SIZE / 2;
        }
        g2d.drawImage(inv_marker, start_marker_x + inv_x * TILE_SIZE, start_marker_y + inv_y * TILE_SIZE, TILE_SIZE,
                TILE_SIZE, this);

        // draw info_box (like mnessage box)
        String l1, l2, l3;
        if (inv_mark_engimon) {
            l1 = "Engimon: ";
            l2 = "Level: ";
            l3 = "Exp | CExp: ";
        } else {
            l1 = "Skill Item: ";
            l2 = "Quantity: ";
            l3 = "Numerik Base Power: ";
        }

        if (inv_mark_engimon) {
            int marked_engimon_idx = (inv_page - 1) * 20 + inv_y * 10 + inv_x;
            int n_engimon = player.get_inventory_engimon().get_list().size();
            if (marked_engimon_idx < n_engimon) {
                Engimon marked_engimon = player.get_inventory_engimon().get_list().get(marked_engimon_idx);
                l1 += marked_engimon.get_name();
                l2 += marked_engimon.get_level();
                l3 += marked_engimon.get_exp() + " | " + marked_engimon.get_cexp();

            }
        } else {
            // Skill Item case
            int marked_si_idx = (inv_page - 1) * 20 + inv_y * 10 + inv_x;
            int n_si = player.get_inventory_skill_item().get_list().size();
            if (marked_si_idx < n_si) {
                SkillItem si = player.get_inventory_skill_item().get_list().get(marked_si_idx);
                l1 += si.get_skill().get_name();
                l2 += si.get_quantity();
                l3 += si.get_nbpower();

            }

        }

        // System.out.println("...........draw mbox");
        Image bg_m_box = new ImageIcon("./images/background.png").getImage();
        g2d.drawImage(bg_m_box, 0 * TILE_SIZE, 12 * TILE_SIZE, SCREEN_WIDTH, 3 * TILE_SIZE, this);
    
        int font_size = 16;
        Font font = new Font("Serif", Font.PLAIN, font_size);
        g2d.setFont(font);
        g2d.drawString(l1, TILE_SIZE / 2, 13 * TILE_SIZE - TILE_SIZE / 5);
        g2d.drawString(l2, TILE_SIZE / 2, 14 * TILE_SIZE - TILE_SIZE / 5 - 8);
        g2d.drawString(l3, TILE_SIZE / 2, 15 * TILE_SIZE - TILE_SIZE / 5 - 16);
        g2d.drawString("page: " + Integer.toString(inv_page) + "/5", 12 * TILE_SIZE, 13 * TILE_SIZE - TILE_SIZE / 5);
        g2d.drawString(inv_status, 9*TILE_SIZE, 14*TILE_SIZE);
    }

    public void draw_landscape(Graphics2D g2d) {
        // then draw from data
        String base_path = "./images/landscape/";
        for (int i = 0; i < this.landscape.length; i++) {
            for (int j = 0; j < this.landscape[i].length; j++) {
                Image landscape_cell;
                switch (this.landscape[i][j]) {
                case 1:
                    landscape_cell = new ImageIcon(base_path + "mountain.png").getImage();
                    break;
                case 2:
                    landscape_cell = new ImageIcon(base_path + "water.png").getImage();
                    break;
                case 3:
                    landscape_cell = new ImageIcon(base_path + "grassland.png").getImage();
                    break;
                case 4:
                    landscape_cell = new ImageIcon(base_path + "ice.png").getImage();
                    break;

                default:
                    landscape_cell = new ImageIcon(base_path + "background.png").getImage();
                    break;
                }
                g2d.drawImage(landscape_cell, j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);

            }
        }

    }

    public void draw_characters(Graphics2D g2d) {
        // #PATH
        String element = "";
        Engimon ep = player.get_engimon();
        if (ep.is_fire() && ep.is_electric()){
            element = "fire_electric";
        } else if (ep.is_water() && ep.is_ice()){
            element = "water_ice";
        } else if (ep.is_water() && ep.is_ground()){
            element = "water_earth";
        } else if (ep.is_fire()){
            element = "fire";
        } else if (ep.is_electric()){
            element = "electric";
        } else if (ep.is_water()){
            element = "water";
        } else if (ep.is_ice()){
            element = "ice";
        } else if (ep.is_ground()){
            element = "earth";
        } 
        Image player = new ImageIcon("./images/transparent/player.gif").getImage();
        Image active_engimon = new ImageIcon("./images/transparent/engimon_" + element + ".gif")
                .getImage();
        // Image enemy1 = new
        // ImageIcon("./src/images/transparent/engimon_earth.gif").getImage();

        // g2d.drawImage(enemy1, 0, 0, TILE_SIZE, TILE_SIZE, this);

        g2d.drawImage(player, this.player_x, this.player_y, TILE_SIZE, TILE_SIZE, this);
        g2d.drawImage(active_engimon, this.active_engimon_x, this.active_engimon_y, TILE_SIZE, TILE_SIZE, this);
        Image e_marker = new ImageIcon("./images/engimon_marker.png").getImage();
        g2d.drawImage(e_marker, this.active_engimon_x, this.active_engimon_y, TILE_SIZE, TILE_SIZE, this);
                
    }

    public void draw_enemies(Graphics2D g2d) {
        for (Engimon e : list_engimon_enemy) {
            Image enemy_image;
            int x = e.get_pos().get_x();
            int y = e.get_pos().get_y();
            if (e.is_fire()) {
                enemy_image = new ImageIcon("./images/transparent/engimon_fire.gif").getImage();
            } else if (e.is_water()) {
                enemy_image = new ImageIcon("./images/transparent/engimon_water.gif").getImage();
            } else if (e.is_ground()) {
                enemy_image = new ImageIcon("./images/transparent/engimon_earth.gif").getImage();
            } else {
                enemy_image = new ImageIcon("./images/transparent/engimon_ice.gif").getImage();
            }
            // enemy_image = new ImageIcon("./images/transparent/engimon_" + sp +
            // ".gif").getImage();
            g2d.drawImage(enemy_image, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
        }

    }

    private void move(String direction) {
        if (is_movement_valid(direction)) {

            if (direction.equals("LEFT")) {
                this.active_engimon_x = this.player_x;
                this.active_engimon_y = this.player_y;
                this.player_x -= TILE_SIZE;
            } else if (direction.equals("RIGHT")) {
                this.active_engimon_x = this.player_x;
                this.active_engimon_y = this.player_y;
                this.player_x += TILE_SIZE;
            } else if (direction.equals("UP")) {
                this.active_engimon_x = this.player_x;
                this.active_engimon_y = this.player_y;
                this.player_y -= TILE_SIZE;
            } else if (direction.equals("DOWN")) {
                this.active_engimon_x = this.player_x;
                this.active_engimon_y = this.player_y;
                this.player_y += TILE_SIZE;
            }

            // gerakkan engimon enemy
            // moveEngimonEnemy();
        }
    }

    private boolean is_movement_valid(String direction) {
        if (direction.equals("LEFT") && this.player_x > 0) {
            return true;
        } else if (direction.equals("RIGHT") && this.player_x < (SCREEN_WIDTH) - (1 * TILE_SIZE)) {
            return true;
        } else if (direction.equals("UP") && this.player_y > 0) {
            return true;
        } else if (direction.equals("DOWN") && this.player_y < (SCREEN_HEIGHT) - (1 * TILE_SIZE)) {
            return true;
        }
        return false;
    } 

    private void test_print(String s) {
        System.out.println("Tes tes.. " + s);
    }

    // control
    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            // System.out.println("keypressed");
            if (current_state == STATE_MAIN_MENU) {
                System.out.println("in main menu");
                System.out.println(key);
                System.out.println(KeyEvent.VK_ENTER);
                if (key == KeyEvent.VK_ENTER) {
                    current_state = STATE_EXPLORE_WORLD;
                    repaint();
                }

            } else if (current_state == STATE_EXPLORE_WORLD) {
                if (flag_message_box == true) {
                    if (key == KeyEvent.VK_ENTER) {
                        clear_message_box();
                    }
                } else if (flag_inventory == true) {
                    if (key == KeyEvent.VK_E) {
                        flag_inventory = false;
                    } else if (key == KeyEvent.VK_RIGHT) {
                        if (inv_x < 9) {
                            inv_x += 1;
                            inv_status = "";
                        } else if (inv_page < inv_max_page) {
                            inv_page++;
                            inv_x = 0;
                            inv_status = "";
                        }
                    } else if (key == KeyEvent.VK_LEFT) {
                        if (inv_x > 0) {
                            inv_x -= 1;
                            inv_status = "";
                        } else if (inv_page > 1) {
                            inv_page--;
                            inv_x = 9;
                            inv_status = "";
                        }
                    } else if (key == KeyEvent.VK_UP) {
                        if (inv_y > 0) {
                            inv_y -= 1;
                            inv_status = "";
                        } else if (inv_mark_engimon == false) {
                            inv_mark_engimon = true;
                            inv_y = 1;
                            inv_status = "";
                        }
                    } else if (key == KeyEvent.VK_DOWN) {
                        if (inv_y < 1) {
                            inv_y += 1;
                            inv_status = "";
                        } else if (inv_mark_engimon == true) {
                            inv_mark_engimon = false;
                            inv_y = 0;
                            inv_status = "";
                        }
                    } else if (key == KeyEvent.VK_G) {
                        int idx = (inv_page - 1) * 20 + inv_y * 10 + inv_x;
                        if (inv_mark_engimon) {
                            if (idx < player.get_inventory_engimon().size()) {
                                if (idx == player.get_inventory_engimon().get_index(player.get_engimon())) {
                                    inv_status = "You can't free active engimon!";
                                }else{
                                    inv_status = "You have freed " + player.get_inventory_engimon().get_item(idx).get_name();
                                    player.free_engimon(idx);
                                }
                            } else {
                                inv_status = "No Engimon is selected!";
                            }
                        } else {
                            if (idx < player.get_inventory_skill_item().size()) {
                                inv_status = "You have thrown " + player.get_inventory_skill_item().get_item(idx).get_name() + " X 1";
                                player.remove_skillitem(idx, 1);
                            } else {
                                inv_status = "No Skill Item is selected!";
                            }
                        }
                    }else if (key == KeyEvent.VK_B){
                        if (inv_mark_engimon == true){
                            int idx = (inv_page-1)*20 + inv_y*10 + inv_x;
                            if (player.get_inventory_engimon().get_item(idx).get_level() >= 4 ){
                                arr_to_breed.add(idx);
                            }
                            if (arr_to_breed.size() == 2){
                                ready_breed = true;
                            }
                            if (ready_breed){
                                try {
                                    player.breed(arr_to_breed.get(0),arr_to_breed.get(1));
                                    inv_status = "You have breed new engimon";
                                } catch (InsufficientLevelException e1) {
                                    inv_status = "Insufficient parent level";
                                } catch (SkillFullException e1) {

                                } catch (ElementNotSuitableException e1) {
                                    inv_status = "Element Not Suitable";
                                }
                                arr_to_breed.clear();
                                ready_breed = false;
                            }

                        }   
                    }
                    
                    else if (key == KeyEvent.VK_C) {
                        change_engimon_handler();
                    }
                } else {
                    if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                        move("LEFT");
                    } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                        move("RIGHT");
                    } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                        move("UP");
                    } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                        move("DOWN");
                    } else if (key == KeyEvent.VK_ESCAPE) {
                        current_state = STATE_MAIN_MENU;
                    } else if (key == KeyEvent.VK_B) {
                        update_state();
                        System.out.println(
                                "###########################\n##########BATTLE###########\n###########################");
                        if (battle_ready) {
                            current_state = STATE_BATTLE;
                            repaint();
                        }
                    } else if (key == KeyEvent.VK_I) {
                        interact();
                        // flag_message_box = !flag_message_box;
                    } else if (key == KeyEvent.VK_C) {
                        System.out.println("....draw comlist###################");
                        show_command_list();
                        // flag_message_box = !flag_message_box;
                    } else if (key == KeyEvent.VK_E) {
                        System.out.println(".........Inventory###################");
                        flag_inventory = true;
                        // flag_message_box = !flag_message_box;
                    }
                }

            } else if (current_state == STATE_BATTLE) {
                if (key == KeyEvent.VK_ESCAPE) {
                    massage_battle = 1;
                    current_state = STATE_EXPLORE_WORLD;
                    repaint();
                } else if (key == KeyEvent.VK_F) {
                    fightEnemy();
                } else if (key == KeyEvent.VK_S) {

                } else if (key == KeyEvent.VK_B) {
                    massage_battle = 1;
                }
            }
            repaint();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
