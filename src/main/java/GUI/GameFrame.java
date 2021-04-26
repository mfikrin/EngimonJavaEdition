package GUI;

import GUI.GamePanel;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame(){
        GamePanel main_panel = new GamePanel();

        this.add(main_panel);

        this.setTitle("Engimon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
