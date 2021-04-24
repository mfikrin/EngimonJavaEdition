package Entity.Engimon.WaterGround;

import Entity.Skill.WaterGround.Aquades;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Beloot extends WaterGroundEngimon {
    public Beloot() {
        super();
        try {
            super.add_skill(new Aquades());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
