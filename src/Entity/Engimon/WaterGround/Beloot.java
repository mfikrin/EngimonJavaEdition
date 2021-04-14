package src.Entity.Engimon.WaterGround;

import src.Entity.Skill.WaterGround.Aquades;

public class Beloot extends WaterGroundEngimon{
    public Beloot() {
        super();
        super.add_skill(new Aquades());
    }
}
