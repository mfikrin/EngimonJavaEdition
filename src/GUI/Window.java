package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window {

    public static void main(String[] args) {
        Window ui = new Window();
    }
    public Window(){
        showUI();

    }
    public void showUI(){
        JFrame window = new JFrame();
        window.setSize(800,600);
        window.getContentPane().setBackground(Color.black);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
    }


}
