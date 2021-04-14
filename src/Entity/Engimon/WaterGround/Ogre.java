package src.Entity.Engimon.WaterGround;

import src.Entity.Skill.WaterGround.Mud;

public class Ogre extends WaterGroundEngimon{
    public Ogre() {
        super();
        super.add_skill(new Mud());
    }
}
