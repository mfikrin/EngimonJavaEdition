package src.Entity.Engimon.Water;

import src.Entity.Skill.Water.NaturalSkills.Mizu;

public class Gurame extends WaterEngimon{
    public Gurame() {
        super();
        super.add_skill(new Mizu());
    }
}
