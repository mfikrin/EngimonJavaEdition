package src.Entity;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int get_x(){return x;}
    public int get_y(){return y;}
    public void incr_x(){x++;}
    public void decr_x(){x--;}
    public void incr_y(){y++;}
    public void decr_y(){y--;}
}
