package src.Entity;

import src.Entity.Engimon.Engimon;
import src.Entity.Skill.Skill;

public class SkillItem {
    private Skill contained_skill;
    private int quantity;

    public SkillItem(Skill skill){
        contained_skill = skill.clone();
        quantity = 0;
    }
    public void incr_quantity(){
        quantity++;
    }
    public boolean learn(Engimon learner){
        if (quantity > 0){
            if (learner.add_skill(contained_skill.clone())){
                quantity--;
                return true;
            }
            return false;
        }
        return false;
    }
    public int get_quantity(){
        return quantity;
    }
    //untuk lebih dari 4 skill kayaknya diatur di player atau gui2an
}