package Entity.Engimon.WaterGround;

import Entity.Skill.WaterGround.Mud;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Ogre extends WaterGroundEngimon {
    public Ogre() {
        super();
        try {
            super.add_skill(new Mud());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
