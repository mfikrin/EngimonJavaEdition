package src.Entity.Engimon.Ground;

import src.Entity.Skill.Ground.NaturalSkills.Crush;

public class Airon extends GroundEngimon{
    public Airon() {
        super();
        super.add_skill(new Crush());
    }
}
