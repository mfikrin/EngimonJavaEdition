package Entity.Engimon.Ice;

import Entity.Skill.Ice.NaturalSkills.Snow;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Nytrogyn extends IceEngimon {
    public Nytrogyn() {
        super();
        try {
            super.add_skill(new Snow());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
