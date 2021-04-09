package src.Entity;

import src.Entity.Skill.Skill;

public class SkillItem {
    private Skill contained_skill;
    private int number;

    public SkillItem(Skill skill){
        contained_skill = skill;
        number = 1;
    }
    public void incr_n(){
        number++;
    }
    public void decr_n(){
        number--;
    }
    public int get_number(){
        return number;
    }
    public Skill get_skill(){
        return contained_skill;
    }
}