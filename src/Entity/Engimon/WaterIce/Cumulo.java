package Entity.Engimon.WaterIce;

import Entity.Skill.WaterIce.Slush;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Cumulo extends WaterIceEngimon {
    public Cumulo() {
        super();
        try {
            super.add_skill(new Slush());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
