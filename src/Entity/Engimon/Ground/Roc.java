package Entity.Engimon.Ground;

import Entity.Skill.Ground.NaturalSkills.Sabaku;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Roc extends GroundEngimon {
    public Roc() {
        super();
        dialogue = "RRRRRooocccccc...";
        try {
            super.add_skill(new Sabaku());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
