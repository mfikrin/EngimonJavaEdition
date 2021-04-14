package src.Entity.Engimon.WaterIce;

import src.Entity.Skill.WaterIce.Slush;

public class Cumulo extends WaterIceEngimon{
    public Cumulo() {
        super();
        super.add_skill(new Slush());
    }
}
