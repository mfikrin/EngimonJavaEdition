package Entity.Engimon.WaterIce;

import Entity.Skill.WaterIce.Frigid;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Strato extends WaterIceEngimon {
    public Strato() {
        super();
        dialogue = "Shoooshhh... Shooshhh....";
        try {
            super.add_skill(new Frigid());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
