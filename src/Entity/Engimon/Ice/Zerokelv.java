package Entity.Engimon.Ice;

import Entity.Skill.Ice.NaturalSkills.Sublimation;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Zerokelv extends IceEngimon {
    public Zerokelv() {
        super();
        try {
            super.add_skill(new Sublimation());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
