package Entity.Engimon.Water;

import Entity.Skill.Water.NaturalSkills.Splash;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Hyushark extends WaterEngimon {
    public Hyushark() {
        super();
        try {
            super.add_skill(new Splash());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
