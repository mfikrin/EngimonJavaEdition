package Entity.Engimon;

import java.util.ArrayList;

import Entity.Clone;
import Entity.Skill.Skill;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;
import Entity.Position;
import Entity.Element;

public class Engimon implements Element,Clone<Engimon> {

    static final int ratio_exp = 100;
    static final int max_skills = 4;
    private String name = getClass().getSimpleName();
    private String species = getClass().getSimpleName();
    private int level = 1;
    private int experience = 0;
    private int cumulative_experience = 0;
    private int current_max_exp = 100;
    private int live = 3;
    private String[][] parent = {{"-","-"},{"-","-"}};
    private ArrayList<Skill> list_skill;
    private Position pos = new Position(0,0);


    //Constructor
    public Engimon() {
        list_skill = new ArrayList<>();
    }

    //Copy Constructor
    public Engimon(Engimon other) {
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
        this.pos = new Position(other.pos);
    }

    //Clone
    public Engimon clone() {
        return new Engimon(this);
    }

    //Getter
    public String get_name() {
        return name;
    }

    public int get_level() {
        return level;
    }

    public int get_exp() {
        return experience;
    }

    public int get_cexp() {
        return cumulative_experience;
    }

    public int get_live() {
        return live;
    }

    public Skill get_skill(int i) {
        return list_skill.get(i);
    }

    public ArrayList<Skill> get_all_skill() {
        return list_skill;
    }

    public int get_skill_size() {
        return list_skill.size();
    }

    public String[][] get_parent() {
        return parent;
    }

    public int get_max_exp() {
        return current_max_exp;
    }

    public String get_species() {
        return species;
    }

    public Position get_pos() {
        return pos;
    }

    public double get_sum_skill_power() {
        double sum = 0;
        for(Skill s : list_skill) {
            sum += (s.get_mlevel() * s.get_nbpower());
        }
        return sum;
    }

    //Setter
    public void set_name(String name) {
        this.name = name;
    }

    public void set_level(int level) {
        this.level = level;
    }

    public void set_exp(int experience) {
        this.experience = experience;
    }

    public void set_cexp(int cumulative_experience) {
        this.cumulative_experience = cumulative_experience;
    }

    public void set_live(int live) {
        this.live = live;
    }

    public void add_skill(Skill new_skill) throws SkillFullException, ElementNotSuitableException {
        if (!is_same_element(new_skill)) {
            throw new ElementNotSuitableException(this.name + " isn't suitable with " + new_skill.get_name(), null);
        }
        
        if (list_skill.size() < 4) {
            list_skill.add(new_skill);
        }else {
            throw new SkillFullException(this.name + " has reached it's max skill quantity", null);
        }
    }
    
    public void set_skill(Skill new_skill, int i) {
        list_skill.set(i,new_skill);
    }

    public void set_parent(String parent1, String species1, String parent2, String species2) {
        parent[0][0] = parent1;
        parent[0][1] = species1;
        parent[1][0] = parent2;
        parent[1][1] = species2;
    }

    public void set_max_exp(int current_max_exp) {
        this.current_max_exp = current_max_exp;
    }

    public void set_pos(Position pos) {
        this.pos = pos;
    }

    public void incr_live() {
        live++;
    }

    public void decr_live() {
        live--;
    }

    public void level_up() {
        this.level++;
        this.current_max_exp += ratio_exp;
        if (level%5 == 0) {
            for(Skill iter : list_skill) {
                iter.incr_mlevel();
            }
        }
    }

    public void add_exp(int enemy_level) {
        Double result = ((Double.valueOf(enemy_level)/Double.valueOf(this.level))+1) * 3.14 * 2.78 * 1.618;
        int addition_exp = (int)Math.round(result);
        this.experience += addition_exp;
        this.cumulative_experience += addition_exp;
        if (this.experience >= this.current_max_exp) {
            this.experience -= this.current_max_exp;
            level_up();
        }
    }

    public void level_down() {
        this.cumulative_experience -= (this.current_max_exp - ratio_exp);
        this.level--;
        this.current_max_exp -= ratio_exp;
        this.experience = this.cumulative_experience;
        int pengurang = ratio_exp;
        int i = 0;
        while(i < level-1) {
            this.experience -= pengurang;
            pengurang += ratio_exp;
            i++;
        }
    }

    //Status
    public boolean is_skill_exist(String skill_name) {
        for(int i = 0; i < list_skill.size(); ++i) {
            if (skill_name.equals(list_skill.get(i).get_name())) {
                return true;
            }
        }
        return false;
    }

    public boolean is_same_element(Element element_object) {
        return ((this.is_electric() && element_object.is_electric()) ||
        (this.is_fire() && element_object.is_fire()) ||
        (this.is_ground() && element_object.is_ground()) ||
        (this.is_ice() && element_object.is_ice()) ||
        (this.is_water() && element_object.is_water()));
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


    //Debugging
    public void debug_print() {
        System.out.println("Name : " + name);
        System.out.println("Species : " + species);
        System.out.println("Level : " + level);
        System.out.println("Current EXP : " + experience);
        System.out.println("Current Cumulative EXP : " + cumulative_experience);
        System.out.println("Current Max EXP : " + current_max_exp);
        System.out.println("Live : " + live);
        System.out.println("Parent 1 Info : " + parent[0][0] + " | " + parent[0][1]);
        System.out.println("Parent 2 Info : " + parent[1][0] + " | " + parent[1][1]);
        System.out.println("Skill Info : ");
        for(int i = 0; i < list_skill.size(); ++i) {
            list_skill.get(i).debug_print();
        }
    }
}