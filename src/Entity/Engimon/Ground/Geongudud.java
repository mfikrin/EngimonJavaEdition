package Entity.Engimon.Ground;

import Entity.Skill.Ground.NaturalSkills.Geospit;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Geongudud extends GroundEngimon {
    public Geongudud() {
        super();
        dialogue = "Duddddd....";
        try {
            super.add_skill(new Geospit());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
