package Entity.Engimon.Water;

import Entity.Skill.Water.NaturalSkills.Hydrobeam;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Dolphzig extends WaterEngimon {
    public Dolphzig() {
        super();
        dialogue = "Zigggllleerr!!";
        try {
            super.add_skill(new Hydrobeam());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
