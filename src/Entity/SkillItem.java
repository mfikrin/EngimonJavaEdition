package Entity;

import Entity.Engimon.Engimon;
import Entity.Skill.Skill;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;
import Exception.ZeroQuantityException;

public class SkillItem{
    private Skill contained_skill;
    private int quantity;

    public SkillItem(Skill skill) {
        contained_skill = skill.clone();
        quantity = 1;
    }

    public int get_quantity() {
        return quantity;
    }

    public void incr_quantity() {
        quantity++;
    }
    public void decr_quantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
    public void learn(Engimon learner) throws ElementNotSuitableException, SkillFullException, ZeroQuantityException {
        if (quantity > 0) {
            learner.add_skill(contained_skill.clone());
            quantity--;
        }else {
            throw new ZeroQuantityException("Skill Item Quantity < 0", null);
        }
    }

    public void debug_print() {
        System.out.println("Skill Item Data : ");
        contained_skill.debug_print();
        System.out.println("Quantity : " + quantity);
    }
}