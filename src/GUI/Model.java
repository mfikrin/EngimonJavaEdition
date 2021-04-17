package src.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean inGame = false;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private int req_dx, req_dy;

    private  Image pokemon;

    private int levelData[] = new int[225];

    private short[] screenData;
    public Model() {
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);

    }
    private void loadImages() {
        pokemon = new ImageIcon("./src/GUI/images/ghost.gif").getImage();
        int a = new ImageIcon("./src/GUI/images/ghost.gif").getIconWidth();
        System.out.println(a);
    }

    private void initVariables() {
        try{
            File file = new File("C:\\Users\\HP\\Documents\\Github\\EngimonJavaEdition\\src\\FileEksternal\\map.txt");

            Scanner scan = new Scanner(file);

            int j = 0;
            while(scan.hasNextLine()){
                String getDataString = scan.nextLine();
                for (int i = 0; i < getDataString.length(); i++)
                {
                    char c = getDataString.charAt(i);
                    this.levelData[j] = Character.getNumericValue(c);
                    j+=1;
                }
            }



            scan.close();
        }catch(FileNotFoundException ex){
            System.out.println("File Tidak Ditemukan");
        }
        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(400, 400);

    }
    private void drawPokemon(Graphics2D g2d, int x, int y) {
        g2d.drawImage(pokemon, x, y, 6, 6,this);
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                if (levelData[i]  == 1) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }
                else if (levelData[i]  == 2) {
                    g2d.setColor(new Color(55,55,55));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }
                else if (levelData[i]  == 3) {
                    g2d.setColor(new Color(20,20,20));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }
                else if (levelData[i]  == 4) {
                    g2d.setColor(new Color(100,100,100));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }
                else  if (levelData[i]  == 5) {
                    drawPokemon(g2d,x + 10, y + 10 );
                }


                i++;
            }
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

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
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

