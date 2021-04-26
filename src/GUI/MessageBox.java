package GUI;

import javax.swing.JLabel;

public class MessageBox {
    private final int n_lines = 3;
    private String[] lines;

    public MessageBox(){
        lines = new String[n_lines];
    }

    public void write(String l1, String l2, String l3){
        lines[0] = l1;
        lines[1] = l2;
        lines[2] = l3;
    }

    public String get_l1(){
        return this.lines[0];
    } 
    public String get_l2(){
        return this.lines[1];
    } 
    public String get_l3(){
        return this.lines[2];
    } 

    // public void show(JLabel label,){
    //     label.setText(text);
    // }

}
