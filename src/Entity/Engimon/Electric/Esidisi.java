package Entity.Engimon.Electric;

import Entity.Skill.Electric.NaturalSkills.Gamma;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Esidisi extends ElectricEngimon {
    public Esidisi() {
        super();
        dialogue = "EYSIDISI!";
        try {
            super.add_skill(new Gamma());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
