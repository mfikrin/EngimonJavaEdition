package src.Entity.Engimon.Water;

import src.Entity.Skill.Water.NaturalSkills.Drown;

public class Baychec extends WaterEngimon{
    public Baychec() {
        super();
        super.add_skill(new Drown());
    }
}
