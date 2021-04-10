package src.Entity.Engimon.Water;

import src.Entity.Skill.Water.NaturalSkills.Pressure;

public class Pam extends WaterEngimon{
    public Pam() {
        super();
        super.add_skill(new Pressure());
    }
}
