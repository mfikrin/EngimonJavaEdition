package src.Entity.Skill;

import src.Entity.Clone;
import src.Entity.Element;

public class Skill implements Element,Clone<Skill>{
    private String name = getClass().getSimpleName();
    private int numerik_base_power = 0;
    private int mastery_level = 1; //maks level 3
    static final int max_mastery_level = 3;

    public Skill(){
        super();
    }

    public Skill(Skill other){
        this.name = other.name;
        this.numerik_base_power = other.numerik_base_power;
        this.mastery_level = other.mastery_level;
    }

    public Skill clone(){
        return new Skill(this);
    }

    public int get_nbpower(){return numerik_base_power;}
    public int get_mlevel(){return mastery_level;}
    public String get_name(){return name;}

    public void set_nbpower(int numerik_base_power){this.numerik_base_power = numerik_base_power;}
    public void set_mlevel(int mastery_level){this.mastery_level = mastery_level;}
    public void set_name(String name){this.name= name;}

    public void incr_mlevel(){mastery_level++;}
    //bagian gaboleh mastery lebih dari 3 kayaknya dihandle di player atau engimon atau gui2an

    public boolean is_max_level(){
        return mastery_level == max_mastery_level;
    }

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