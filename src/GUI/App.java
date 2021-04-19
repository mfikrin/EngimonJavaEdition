package src.GUI;


import javax.swing.*;

public class App extends JFrame{
    public Model model;
    public App() {
        model = new Model();
//        System.out.println(model.jumlahGerakan);
        add(model);
    }


    public static void main(String[] args) {
        App pokemon = new App();
//        System.out.println(pokemon.model.jumlahGerakan);
        pokemon.setVisible(true);
        pokemon.setTitle("Pokemon");
        pokemon.setSize(380,420);
//        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        pac.setLocationRelativeTo(null);

    }

}
