package Entity.Engimon.Ground;

import Entity.Skill.Ground.NaturalSkills.Crush;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Airon extends GroundEngimon {
    public Airon() {
        super();
        try {
            super.add_skill(new Crush());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
