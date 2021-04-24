package src.GUI;

import java.lang.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

import jdk.jshell.spi.ExecutionControl.EngineTerminationException;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;

// External Import, moved later
import java.util.*;
import src.Entity.Engimon.*;
import src.Entity.Engimon.Fire.*;
// import src.Entity.Position;

// -------


public class GamePanel extends JPanel implements ActionListener{
    
    private static final int TILE_SIZE = 40;
    private static final int SCREEN_WIDTH = 15*TILE_SIZE;
    private static final int SCREEN_HEIGHT = 15*TILE_SIZE;
    private static final Color BG_COLOR = Color.WHITE;

    // ----------------------------------- //
    private final int STATE_GAME_STOP = -1;
    private final int STATE_PAUSE = 0;
    private final int STATE_MAIN_MENU = 1;
    private final int STATE_EXPLORE_WORLD = 2;
    private final int STATE_BATTLE = 3;
    private final int STATE_BREED = 4;

    private int current_state = STATE_MAIN_MENU;
    // ----------------------------------- //

    private boolean battle_ready = false;
    

    // =================================== //

    private int player_x;
    private int player_y;

    private int active_engimon_x;
    private int active_engimon_y;
    private String active_engimon_type;

    private int landscape[][];

    private ArrayList<Engimon> list_engimon_enemy;

    // --temp
    private final int max_step_count = 5;
    private int step_count = 0;
    // -------
    // =================================== //

    


    // tester atributes, disposed later
    private static int count_i = 0; 
    private boolean is_escape = false;

    // |-END Test Attributes
    

    // EXTERNAL FUNCTIONs, moved later
    private void spawnEngimonEnemy()
    {
        Random randomNumbers = new Random();
        int x = randomNumbers.nextInt(15);
        int y = randomNumbers.nextInt(15);
        Engimon enemy = new Aapee();
        list_engimon_enemy.add(enemy);
    }

    // END External Functions



    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(BG_COLOR);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        init_data();


    }

    private void load_map_data(){
        this.landscape = new int[SCREEN_HEIGHT/TILE_SIZE][SCREEN_WIDTH/TILE_SIZE];
        try{
            // #PATH
            File file = new File("./src/FileEksternal/map.txt");

            Scanner scan = new Scanner(file);

            int i = 0;
            while(scan.hasNextLine()){
                String getDataString = scan.nextLine();
                for (int j = 0; j < getDataString.length(); j++)
                {
                    char c = getDataString.charAt(j);
                    this.landscape[i][j] = Character.getNumericValue(c);
                    System.out.print(this.landscape[i][j]);
                }
                System.out.println();
                i+=1;
            }
            scan.close();
        }catch(FileNotFoundException ex){
            System.out.println("File Tidak Ditemukan");
        }
    }

    private void init_data(){
        // to be replaced by data from gamestate
        this.player_x = 1*TILE_SIZE;
        this.player_y = 0*TILE_SIZE;
        this.active_engimon_x = 0*TILE_SIZE;
        this.active_engimon_y = 0*TILE_SIZE;
        this.active_engimon_type = "electric";
        this.list_engimon_enemy = new ArrayList<Engimon>();
        // END to be...
        
        load_map_data();

    }

    
    
    
    
    
    
    
    
    // Helper functions
    
    private void update_state(){
        // -- cek 
        if (current_state == STATE_EXPLORE_WORLD){
            test_print("check surronding");
            battle_ready = check_surrrounding_enemy();
            System.out.println(battle_ready);
            if (battle_ready){
                System.out.println("Battle?");
            }
        }
        // update engimon enemy
        System.out.println(this.list_engimon_enemy.size());
    }

    private boolean check_surrrounding_enemy(){
        int px = this.player_x/TILE_SIZE;
        int py = this.player_y/TILE_SIZE;
        System.out.println(px);
        System.out.println(py);
        for (Engimon e : this.list_engimon_enemy) {
            int ex = e.get_pos_x();
            int ey = e.get_pos_y();
            // check west, north, east, south
            if ((px == ex+1 && py == ey) || (px == ex-1 && py == ey) || (px == ex && py == ey+1) || (px == ex && py == ey-1)){
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
        update_state();
        
        count_i ++;
        // test_print(Integer.toString(count_i));
        
		super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        System.out.println("in paint");
        System.out.println(current_state+100);
        switch (current_state) {
            case STATE_MAIN_MENU:
                draw_main_menu(g2d);
                break;
            case STATE_EXPLORE_WORLD:
                System.out.println("should draw maps");
                draw_explore_world(g2d);
                break;
            case STATE_BATTLE:
                draw_battle(g2d);
                break;
            
            // ...
            default:
                System.out.println("thrown at blank");
                draw_blank_screen();
                break;
        }
        

	}
    // --experiment
    
    public void draw_blank_screen(){
        System.out.println("Blank");
    }
    // -----
    
    public void draw_battle(Graphics2D g2d){
        System.out.println("Battle");
        Image p = new ImageIcon("./src/images/transparent/engimon_electric.gif").getImage();
        Image e = new ImageIcon("./src/images/transparent/engimon_Earth.gif").getImage();

        Image bg = new ImageIcon("./src/images/battle/bg_3_lives.png").getImage();
        g2d.drawImage(bg, 0,0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
        g2d.drawImage(p, 2*TILE_SIZE,7*TILE_SIZE, 4*TILE_SIZE, 4*TILE_SIZE, this);
        g2d.drawImage(e, 11*TILE_SIZE,5*TILE_SIZE, 2*TILE_SIZE, 2*TILE_SIZE, this);
    }
    
    public void draw_main_menu(Graphics2D g2d){
        System.out.println("main menu");
        Image banner = new ImageIcon("./src/images/title_banner.gif").getImage();
        g2d.drawImage(banner, 0,0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
    }
    public void draw_explore_world(Graphics2D g2d){
        System.out.println("in");
        
        draw_landscape(g2d);
        draw_characters(g2d);
        draw_enemies(g2d);
    }
    public void draw_landscape(Graphics2D g2d){
        // then draw from data
        String base_path = "./src/images/landscape/";
        for (int i=0; i<this.landscape.length; i++){
            for (int j=0; j<this.landscape[i].length; j++){
                Image landscape_cell;
                switch (this.landscape[i][j]) {
                    case 1:
                    landscape_cell = new ImageIcon(base_path+"mountain.png").getImage();
                    break;
                    case 2:
                    landscape_cell = new ImageIcon(base_path+"water.png").getImage();
                    break;
                    case 3:
                    landscape_cell = new ImageIcon(base_path+"grassland.png").getImage();
                    break;
                    case 4:
                    landscape_cell = new ImageIcon(base_path+"ice.png").getImage();
                    break;
                    
                    default:
                    landscape_cell = new ImageIcon(base_path+"background.png").getImage();
                    break;
                }
                g2d.drawImage(landscape_cell, j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                
            }
        }

    }
    public void draw_characters(Graphics2D g2d){
        // #PATH
        Image player = new ImageIcon("./src/images/transparent/player.gif").getImage();
        Image active_engimon = new ImageIcon("./src/images/transparent/engimon_"+this.active_engimon_type+".gif").getImage();
        // Image enemy1 = new ImageIcon("./src/images/transparent/engimon_earth.gif").getImage();
        
        // g2d.drawImage(enemy1, 0, 0, TILE_SIZE, TILE_SIZE, this);

        g2d.drawImage(player, this.player_x, this.player_y, TILE_SIZE, TILE_SIZE, this);
        g2d.drawImage(active_engimon, this.active_engimon_x, this.active_engimon_y, TILE_SIZE, TILE_SIZE, this);

    }
    public void draw_enemies(Graphics2D g2d){
        for (Engimon e : list_engimon_enemy) {
            int x = e.get_pos_x();
            int y = e.get_pos_y();
            // WRONG, need to be fixed
            String sp = e.get_species();
            // |---
            // test_print(sp);
            Image enemy_image = new ImageIcon("./src/images/transparent/engimon_"+sp+".gif").getImage();
            g2d.drawImage(enemy_image, x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE,TILE_SIZE, this);
        }

    }

    private void move(String direction){
        if (is_movement_valid(direction)){

            // --temp
            this.step_count+=1;
            if (this.step_count%this.max_step_count == 1){
                // this.step_count -= this.max_step_count;
                spawnEngimonEnemy();
            }
            // ------

            // Nanti yang diubah bukan value atribut GamePanel, tapi data di backend
            if (direction.equals("LEFT")){
                this.active_engimon_x = this.player_x;
                this.active_engimon_y = this.player_y;
                this.player_x-=TILE_SIZE;   
            } else if (direction.equals("RIGHT")){
                this.active_engimon_x = this.player_x;
                this.active_engimon_y = this.player_y;
                this.player_x+=TILE_SIZE; 
            } else if(direction.equals("UP")){
                this.active_engimon_x = this.player_x;
                this.active_engimon_y = this.player_y;
                this.player_y-=TILE_SIZE;
            } else if (direction.equals("DOWN")){   
                this.active_engimon_x = this.player_x;
                this.active_engimon_y = this.player_y;
                this.player_y+=TILE_SIZE;
            }
        }
    }

    private boolean is_movement_valid(String direction){
        if (direction.equals("LEFT") && this.player_x > 0){
            return true;   
        } else if (direction.equals("RIGHT") && this.player_x < (SCREEN_WIDTH)-(1*TILE_SIZE)){
            return true;   
        } else if(direction.equals("UP") && this.player_y > 0){
            return true;   
        } else if (direction.equals("DOWN") && this.player_y < (SCREEN_HEIGHT)-(1*TILE_SIZE)){   
            return true;   
        }
        return false;
    }

    private void test_print(String s){
        System.out.println("Tes tes.. "+s);
    }

















    // control
    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            System.out.println("keypressed");
            if (current_state == STATE_MAIN_MENU){
                System.out.println("in main menu");
                System.out.println(key);
                System.out.println(KeyEvent.VK_ENTER);
                if (key == KeyEvent.VK_ENTER){
                    current_state = STATE_EXPLORE_WORLD;
                    repaint();
                }


            } else if (current_state == STATE_EXPLORE_WORLD){
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                    move("LEFT");
                } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                    move("RIGHT");
                } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                    move("UP");
                } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                    move("DOWN");
                } 
                else if (key == KeyEvent.VK_ESCAPE){
                    current_state = STATE_MAIN_MENU;
                }
                else if (key == KeyEvent.VK_B){
                    if(battle_ready){
                        current_state = STATE_BATTLE;
                        repaint();
                    }
                }




            } else if (current_state == STATE_BATTLE){
                if (key == KeyEvent.VK_ESCAPE){
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
}