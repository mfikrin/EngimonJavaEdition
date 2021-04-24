package Entity.Engimon.Ice;

import Entity.Skill.Ice.NaturalSkills.Chill;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Buckoo extends IceEngimon {
    public Buckoo() {
        super();
        try {
            super.add_skill(new Chill());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
