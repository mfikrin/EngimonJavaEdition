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
    private int live = 1; //kalau liar 1 kalau active 3
    private String[][] parent = new String[2][2];
    private ArrayList<Skill> list_skill;

    public Engimon(){
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
        this.parent = other.parent.clone();
        this.list_skill = new ArrayList<>();
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
    public String[][] get_parent(){return parent;}
    public int get_max_exp(){return current_max_exp;}
    public String get_species(){return species;}

    public void set_name(String name){this.name = name;}
    public void set_level(int level){this.level = level;}
    public void set_exp(int experience){this.experience = experience;}
    public void set_cexp(int cumulative_experience){this.cumulative_experience = cumulative_experience;}
    public void set_live(int live){this.live = live;}
    public boolean add_skill(Skill new_skill){
        if (!is_same_element(new_skill)){
            return false;
        }
        if (list_skill.size() < max_skills){
            list_skill.add(new_skill);
            return true;
        }
        return false;
    }

    public boolean is_same_element(Element element_object){
        return ((this.is_electric() && element_object.is_electric()) ||
        (this.is_fire() && element_object.is_fire()) ||
        (this.is_ground() && element_object.is_ground()) ||
        (this.is_ice() && element_object.is_ice()) ||
        (this.is_water() && element_object.is_water()));
    }
    
    public void set_skill(Skill new_skill, int i){list_skill.set(i,new_skill);}
    public void set_parent(String parent1, String species1, String parent2, String species2){
        parent[0][0] = parent1;
        parent[0][1] = species1;
        parent[1][0] = parent2;
        parent[1][1] = species2;
    }
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