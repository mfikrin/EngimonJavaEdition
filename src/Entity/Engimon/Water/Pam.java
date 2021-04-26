package Entity.Engimon.Water;

import Entity.Skill.Water.NaturalSkills.Pressure;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Pam extends WaterEngimon {
    public Pam() {
        super();
        dialogue = "Pam Pam Pam!";
        try {
            super.add_skill(new Pressure());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
