package src.Entity.Engimon.Water;

import src.Entity.Skill.Water.NaturalSkills.Splash;

public class Hyushark extends WaterEngimon{
    public Hyushark() {
        super();
        super.add_skill(new Splash());
    }
}
