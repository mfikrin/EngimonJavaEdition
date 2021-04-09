package src.Entity;

public class Skill {
    private String name;
    private int numerik_base_power;
    private int mastery_level; //maks level 3

    public Skill(String name, int numerik_base_power, int mastery_level){
        this.name = name;
        this.numerik_base_power = numerik_base_power;
        this.mastery_level = mastery_level;
    }

    public int get_nbpower(){return numerik_base_power;}
    public int get_mlevel(){return mastery_level;}
    public String get_name(){return name;}

    public void set_nbpower(int numerik_base_power){this.numerik_base_power = numerik_base_power;}
    public void set_mlevel(int mastery_level){this.mastery_level = mastery_level;}
    public void set_name(String name){this.name= name;}
}