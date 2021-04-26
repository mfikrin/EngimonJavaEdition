package Entity.Engimon.Electric;

import Entity.Skill.Electric.NaturalSkills.Thunderbolt;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Peelen extends ElectricEngimon {
    public Peelen() {
        super();
        dialogue = "Peelellelelle....";
        try {
            super.add_skill(new Thunderbolt());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
