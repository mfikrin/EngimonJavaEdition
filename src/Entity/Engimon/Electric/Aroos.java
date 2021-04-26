package Entity.Engimon.Electric;

import Entity.Skill.Electric.NaturalSkills.Electrons;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Aroos extends ElectricEngimon {
    public Aroos() {
        super();
        dialogue = "Rooosss.... Roosss....";
        try {
            super.add_skill(new Electrons());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
