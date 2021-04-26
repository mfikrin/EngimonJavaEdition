package Entity.Engimon.Electric;

import Entity.Skill.Electric.NaturalSkills.Charge;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Choolok extends ElectricEngimon {
    public Choolok() {
        super();
        dialogue = "Chooooo... Chooo... LOK!";
        try {
            super.add_skill(new Charge());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
