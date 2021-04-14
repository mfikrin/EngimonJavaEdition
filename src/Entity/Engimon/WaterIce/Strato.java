package src.Entity.Engimon.WaterIce;

import src.Entity.Skill.WaterIce.Frigid;

public class Strato extends WaterIceEngimon{
    public Strato() {
        super();
        super.add_skill(new Frigid());
    }
}
