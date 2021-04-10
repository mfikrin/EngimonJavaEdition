package src.Entity.Engimon.Ground;

import src.Entity.Skill.Ground.NaturalSkills.Dig;

public class Gambut extends GroundEngimon{
    public Gambut() {
        super();
        super.add_skill(new Dig());
    }
}
