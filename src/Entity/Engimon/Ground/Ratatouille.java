package src.Entity.Engimon.Ground;

import src.Entity.Skill.Ground.NaturalSkills.Terroar;

public class Ratatouille extends GroundEngimon {
    public Ratatouille() {
        super();
        super.add_skill(new Terroar());
    }
}
