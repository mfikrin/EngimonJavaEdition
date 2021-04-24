package Entity.Engimon.Water;

import Entity.Skill.Water.NaturalSkills.Drown;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Baychec extends WaterEngimon {
    public Baychec() {
        super();
        try {
            super.add_skill(new Drown());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
