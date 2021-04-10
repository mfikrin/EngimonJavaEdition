package src.Entity.Skill;

import src.Entity.Element;

public class Skill implements Element{
    private String name = getClass().getSimpleName();
    private int numerik_base_power = 0;
    private int mastery_level = 1; //maks level 3

    public int get_nbpower(){return numerik_base_power;}
    public int get_mlevel(){return mastery_level;}
    public String get_name(){return name;}

    public void set_nbpower(int numerik_base_power){this.numerik_base_power = numerik_base_power;}
    public void set_mlevel(int mastery_level){this.mastery_level = mastery_level;}
    public void set_name(String name){this.name= name;}

    @Override
    public boolean is_fire() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean is_water() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean is_electric() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean is_ground() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean is_ice() {
        // TODO Auto-generated method stub
        return false;
    }
}