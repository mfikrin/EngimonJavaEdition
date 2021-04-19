package src.GUI;

import src.Entity.Engimon.Electric.Aroos;
import src.Entity.Engimon.Engimon;
import src.Entity.Engimon.Fire.Aapee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean inGame = true;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private int req_dx, req_dy;

//    image
    private  Image pokemonImage;
//    engimon
    private Engimon pokemongo;
    private ArrayList<Engimon> engimonEnemy;

    public int jumlahGerakan = 0;


    private int levelData[][] = new int[15][15];
    public Model() {

        loadFirstEngimon();
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);

    }
    private void loadImages() {
        pokemonImage = new ImageIcon("./src/GUI/images/ghost.gif").getImage();

    }

    private void spawnEngimonEnemy()
    {
        Random randomNumbers = new Random();
        int x = randomNumbers.nextInt(15);
        int y = randomNumbers.nextInt(15);
        Engimon enemy =  new Aapee();
        enemy.get_position().setPosition(x,y);
        engimonEnemy.add(enemy);

    }


    private void loadFirstEngimon()
    {
//        ini masih statik ngeluarin across, nanti dibuat random
        this.pokemongo = new Aroos();
    }


    private void initVariables() {
        try{
            File file = new File("C:\\Users\\HP\\Documents\\Github\\EngimonJavaEdition\\src\\FileEksternal\\map.txt");

            Scanner scan = new Scanner(file);

            int i = 0;
            while(scan.hasNextLine()){
                String getDataString = scan.nextLine();
                for (int j = 0; j < getDataString.length(); j++)
                {
                    char c = getDataString.charAt(j);
                    this.levelData[i][j] = Character.getNumericValue(c);
                }
                i+=1;
            }



            scan.close();
        }catch(FileNotFoundException ex){
            System.out.println("File Tidak Ditemukan");
        }
        d = new Dimension(400, 400);

    }
    private void drawPokemon(Graphics2D g2d, int x, int y) {
        g2d.drawImage(pokemonImage, x, y, 6, 6,this);
    }

    private void drawEngimonEnemy(Graphics2D g2d, int x, int y) {
        g2d.drawImage(pokemonImage, x, y, 6, 6,this);
    }

    private Boolean isEnemyHere(int i, int j)
    {
        for (Engimon enemy : engimonEnemy)
        {
            if (enemy.get_position().get_x() == j && enemy.get_position().get_y() == i )
            {
                return true;
            }
        }

        return false;
    }



    private void drawMaze(Graphics2D g2d) {
//        System.out.println(jumlahGerakan);
        int i = 0;
        int x, y,z;
        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            int j = 0;
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {
                if (j == pokemongo.get_position().get_x() && i == pokemongo.get_position().get_y())
                {
                    drawPokemon(g2d,x + 10, y + 10 );
                }
//                else if (isEnemyHere(i,j))
//                {
//                    drawEngimonEnemy(g2d,x + 10, y + 10 );
//                }
                else{
                    if (levelData[i][j]  == 1) {
                        g2d.setColor(new Color(255,255,255));
                        g2d.fillOval(x + 10, y + 10, 6, 6);
                    }
                    else if (levelData[i][j]  == 2) {
                        g2d.setColor(new Color(55,55,55));
                        g2d.fillOval(x + 10, y + 10, 6, 6);
                    }
                    else if (levelData[i][j]  == 3) {
                        g2d.setColor(new Color(20,20,20));
                        g2d.fillOval(x + 10, y + 10, 6, 6);
                    }
                    else if (levelData[i][j]  == 4) {
                        g2d.setColor(new Color(100,100,100));
                        g2d.fillOval(x + 10, y + 10, 6, 6);
                    }
                }
                j+=1;
            }
            i+=1;
        }
        if (jumlahGerakan % 5 == 1)
        {
            System.out.println(jumlahGerakan);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);

    }



    //controls
    class TAdapter extends KeyAdapter {




        @Override
        public void keyPressed(KeyEvent e) {
            jumlahGerakan +=1;

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    pokemongo.get_position().decr_x();
                } else if (key == KeyEvent.VK_RIGHT) {
                    pokemongo.get_position().incr_x();
                } else if (key == KeyEvent.VK_UP) {
                    pokemongo.get_position().decr_y();
                } else if (key == KeyEvent.VK_DOWN) {
                    pokemongo.get_position().incr_y();
                }
            } else {
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}

