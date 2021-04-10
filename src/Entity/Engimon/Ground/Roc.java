package src.Entity.Engimon.Ground;

import src.Entity.Skill.Ground.NaturalSkills.Sabaku;

public class Roc extends GroundEngimon{
    public Roc() {
        super();
        super.add_skill(new Sabaku());
    }
}
