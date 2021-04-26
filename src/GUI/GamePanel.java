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
import Entity.Map;
import Entity.Engimon.*;
import Entity.Engimon.Fire.*;
import Entity.Engimon.Electric.*;
import Entity.Engimon.FireElectric.*;
import Entity.Engimon.Ground.*;
import Entity.Engimon.Ice.*;
import Entity.Engimon.Water.*;
import Entity.Engimon.WaterGround.*;
import Entity.Engimon.WaterIce.*;
// import src.Entity.Position;

import Generator.EngimonGenerator;

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

    // FLAGS |=~
    private boolean flag_message_box;
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
        list_engimon_enemy.add(enemy);
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
        return P.get_x() == player_x && P.get_y() == player_y;
    }

    public Boolean isHitEngimonPlayer(Position P) {
        return P.get_x() == active_engimon_x && P.get_y() == active_engimon_y;
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
        // to be replaced by data from gamestate
        this.player_x = 1 * TILE_SIZE;
        this.player_y = 0 * TILE_SIZE;
        this.active_engimon_x = 0 * TILE_SIZE;
        this.active_engimon_y = 0 * TILE_SIZE;
        this.active_engimon_type = "electric";
        this.list_engimon_enemy = new LinkedList<Engimon>();
        this.message_box = new MessageBox();

        // set flag(s)
        this.flag_message_box = false;
        // END to be...

        load_map_data();

    }

    // Helper functions

    private void clear_message_box() {
        this.flag_message_box = false;
        message_box.write("", "", "");
    }

    private void engimon_interract() {
        this.flag_message_box = true;
        message_box.write("Halo!", "aku engimon", "");
        System.out.println(".............interacttt");

    }

    public void show_command_list(){
        this.flag_message_box = true;
        message_box.write("Navigasi: w/a/s/d atau tanda panah", "B: Battle     E: Inventory", "I: Interact     C: Check active Engimon");
    }

    public void interact(){
        this.flag_message_box = true;
        message_box.write("Halo!", "Saya " + active_engimon_type, "");
    }

    private void update_state() {
        // -- cek
        if (current_state == STATE_EXPLORE_WORLD) {
            // test_print("check surronding");
            battle_ready = check_surrrounding_enemy();
            System.out.println(battle_ready);
            if (battle_ready) {
                System.out.println("Battle?");
            }
        }
        // update engimon enemy
        System.out.println(this.list_engimon_enemy.size());
    }

    private boolean check_surrrounding_enemy() {
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
                return true;
            }
        }
        return false;
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
            draw_battle(g2d);
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
        System.out.println("Battle");
        Image p = new ImageIcon("./images/transparent/engimon_electric.gif").getImage();
        Image e = new ImageIcon("./images/transparent/engimon_Earth.gif").getImage();

        Image bg = new ImageIcon("./images/battle/bg_3_lives.png").getImage();
        g2d.drawImage(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
        g2d.drawImage(p, 2 * TILE_SIZE, 7 * TILE_SIZE, 4 * TILE_SIZE, 4 * TILE_SIZE, this);
        g2d.drawImage(e, 11 * TILE_SIZE, 5 * TILE_SIZE, 2 * TILE_SIZE, 2 * TILE_SIZE, this);
    }

    public void draw_main_menu(Graphics2D g2d) {
        // System.out.println("main menu");
        Image banner = new ImageIcon("./images/title_banner.gif").getImage();
        g2d.drawImage(banner, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
        Image e1 = new ImageIcon("./images/transparent/engimon_Aapee.gif").getImage();
        Image e2 = new ImageIcon("./images/transparent/engimon_electric.gif").getImage();
        Image e3 = new ImageIcon("./images/transparent/engimon_Earth.gif").getImage();
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
        }
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
        Image player = new ImageIcon("./images/transparent/player.gif").getImage();
        Image active_engimon = new ImageIcon("./images/transparent/engimon_" + this.active_engimon_type + ".gif")
                .getImage();
        // Image enemy1 = new
        // ImageIcon("./src/images/transparent/engimon_earth.gif").getImage();

        // g2d.drawImage(enemy1, 0, 0, TILE_SIZE, TILE_SIZE, this);

        g2d.drawImage(player, this.player_x, this.player_y, TILE_SIZE, TILE_SIZE, this);
        g2d.drawImage(active_engimon, this.active_engimon_x, this.active_engimon_y, TILE_SIZE, TILE_SIZE, this);

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
                enemy_image = new ImageIcon("./images/transparent/engimon_Earth.gif").getImage();
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

            // --temp
            // this.step_count += 1;
            // if (this.step_count % this.max_step_count == 1) {
            // // this.step_count -= this.max_step_count;
            // spawnEngimonEnemy();
            // }
            // ------
            // Nanti yang diubah bukan value atribut GamePanel, tapi data di backend
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
            moveEngimonEnemy();
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
                        if (battle_ready) {
                            current_state = STATE_BATTLE;
                            repaint();
                        }
                    } else if (key == KeyEvent.VK_I) {
                        engimon_interract();
                        // flag_message_box = !flag_message_box;
                    }
                }

            } else if (current_state == STATE_BATTLE) {
                if (key == KeyEvent.VK_ESCAPE) {
                    current_state = STATE_EXPLORE_WORLD;
                    repaint();
                }
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public MessageBox show_command_list() {
        MessageBox command_list = new MessageBox();
        command_list.write("Navigasi: w/a/s/d atau tanda panah", "B: Battle     E: Inventory",
                "I: Interact     C: Check active Engimon");
        return command_list;
    }

    public MessageBox interact() {
        MessageBox interact = new MessageBox();
        interact.write("Halo!", "Saya " + active_engimon_type, "");
        return interact;
    }
}
