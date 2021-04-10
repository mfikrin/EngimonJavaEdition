package src.Entity.Engimon;

import java.util.ArrayList;

import src.Entity.Clone;
import src.Entity.Element;
import src.Entity.Skill.Skill;

public class Engimon implements Element,Clone<Engimon>{

    static final int ratio_exp = 100;
    static final int max_skills = 4;
    private String name = getClass().getSimpleName();
    private String species = getClass().getSimpleName();
    private int level = 1;
    private int experience = 0;
    private int cumulative_experience = 0;
    private int current_max_exp = 100;
    private int live = 3;
    private ArrayList<ArrayList<String>> parent;
    private ArrayList<Skill> list_skill;

    public Engimon(){
        parent = new ArrayList<>();
        list_skill = new ArrayList<>();
    }

    public Engimon(Engimon other){
        this.name = other.name;
        this.species = other.species;
        this.level = other.level;
        this.experience = other.experience;
        this.cumulative_experience = other.cumulative_experience;
        this.current_max_exp = other.current_max_exp;
        this.live = other.live;
        this.parent = new ArrayList<>();
        this.list_skill = new ArrayList<>();
        //clone parent blom
        for (Skill skill : other.list_skill) {
            this.list_skill.add(skill.clone());
        }
    }

    public Engimon clone(){
        return new Engimon(this);
    }

    public String get_name(){ return name;}
    public int get_level(){return level;}
    public int get_exp(){return experience;}
    public int get_cexp(){return cumulative_experience;}
    public int get_live(){return live;}
    public Skill get_skill(int i){return list_skill.get(i);}
    public int get_skill_size(){return list_skill.size();}
    public ArrayList<ArrayList<String>> get_parent(){return parent;}
    public int get_max_exp(){return current_max_exp;}
    public String get_species(){return species;}

    public void set_name(String name){this.name = name;}
    public void set_level(int level){this.level = level;}
    public void set_exp(int experience){this.experience = experience;}
    public void set_cexp(int cumulative_experience){this.cumulative_experience = cumulative_experience;}
    public void set_live(int live){this.live = live;}
    public boolean add_skill(Skill new_skill){
        if (list_skill.size() < max_skills){
            list_skill.add(new_skill);
            return true;
        }
        return false;
    }
    //bagian gaboleh lebih dari 4 skill kayaknya diatur di player atau gui2an
    public void set_skill(Skill new_skill, int i){list_skill.set(i,new_skill);}
    public void add_parent(ArrayList<String> parent_){parent.add(parent_);}
    public void set_max_exp(int current_max_exp){this.current_max_exp = current_max_exp;}

    public void incr_live(){live++;}
    public void decr_live(){live--;}


    public void level_up(){
        this.level++;
        this.current_max_exp += ratio_exp;
    }
    public void add_exp(int enemy_level){
        Double result = ((Double.valueOf(enemy_level)/Double.valueOf(this.level))+1) * 3.14 * 2.78 * 1.618;
        int addition_exp = (int)Math.round(result);
        this.experience += addition_exp;
        this.cumulative_experience += addition_exp;
        if (this.experience >= this.current_max_exp){
            this.experience -= this.current_max_exp;
            level_up();
        }
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