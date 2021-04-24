package Entity.Engimon.Water;

import Entity.Skill.Water.NaturalSkills.Mizu;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Gurame extends WaterEngimon {
    public Gurame() {
        super();
        try {
            super.add_skill(new Mizu());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
