package Entity.Skill;

import Entity.Element;
import Entity.Clone;

public class Skill implements Element,Clone<Skill> {
    private String name = getClass().getSimpleName();
    private int numerik_base_power = 0;
    private int mastery_level = 1;
    static final int max_mastery_level = 3;

    //Constructor
    public Skill() {
        super();
    }

    //Copy Constructor
    public Skill(Skill other) {
        this.name = other.name;
        this.numerik_base_power = other.numerik_base_power;
        this.mastery_level = other.mastery_level;
    }

    //Clone
    public Skill clone() {
        return new Skill(this);
    }

    //Getter
    public int get_nbpower() {
        return numerik_base_power;
    }

    public int get_mlevel() {
        return mastery_level;
    }

    public String get_name() {
        return name;
    }

    //Setter
    public void set_nbpower(int numerik_base_power) {
        this.numerik_base_power = numerik_base_power;
    }

    public void set_mlevel(int mastery_level) {
        this.mastery_level = mastery_level;
    }

    public void set_name(String name) {
        this.name= name;
    }

    public void incr_mlevel() {
        if (mastery_level < max_mastery_level) {
            numerik_base_power += 4;
            mastery_level++;
        }
    }
    
    //Status
    public boolean is_max_level() {
        return mastery_level == max_mastery_level;
    }

    @Override
    public boolean is_fire() {
        return false;
    }

    @Override
    public boolean is_water() {
        return false;
    }

    @Override
    public boolean is_electric() {
        return false;
    }

    @Override
    public boolean is_ground() {
        return false;
    }

    @Override
    public boolean is_ice() {
        return false;
    }

    //Debug
    public void debug_print() {
        System.out.println("Skill Name : " + name);
        System.out.print("Element(s) : ");
        if (is_fire() && is_electric()) {
            System.out.println("Fire/Electric");
        }else if (is_water() && is_ground()) {
            System.out.println("Water/Ground");
        }else if (is_water() && is_ice()) {
            System.out.println("Water/Ice");
        }else if (is_fire()) {
            System.out.println("Fire");
        }else if (is_water()) {
            System.out.println("Water");
        }else if (is_electric()) {
            System.out.println("Electric");
        }else if (is_ground()) {
            System.out.println("Ground");
        }else if (is_ice()) {
            System.out.println("Ice");
        }
        System.out.println("Numerik Base Power : " + numerik_base_power);
        System.out.println("Mastery Level : " + mastery_level);
    }
}