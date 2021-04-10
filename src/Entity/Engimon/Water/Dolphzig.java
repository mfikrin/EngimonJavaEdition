package src.Entity.Engimon.Water;

import src.Entity.Skill.Water.NaturalSkills.Hydrobeam;

public class Dolphzig extends WaterEngimon{
    public Dolphzig() {
        super();
        super.add_skill(new Hydrobeam());
    }
}
