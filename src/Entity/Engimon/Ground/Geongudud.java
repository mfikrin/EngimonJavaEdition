package src.Entity.Engimon.Ground;

import src.Entity.Skill.Ground.NaturalSkills.Geospit;

public class Geongudud extends GroundEngimon {
    public Geongudud() {
        super();
        super.add_skill(new Geospit());
    }
}
