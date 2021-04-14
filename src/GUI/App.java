package src.GUI;


import javax.swing.*;

public class App extends JFrame{

    public App() {
        add(new Model());
    }


    public static void main(String[] args) {
        App pokemon = new App();
        pokemon.setVisible(true);
        pokemon.setTitle("Pokemon");
        pokemon.setSize(380,420);
//        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        pac.setLocationRelativeTo(null);

    }

}
