package Entity.Engimon.Ice;

import Entity.Skill.Ice.NaturalSkills.Cold;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Kuulkahs extends IceEngimon {
    public Kuulkahs() {
        super();
        try {
            super.add_skill(new Cold());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
