package Entity.Engimon.Ground;

import Entity.Skill.Ground.NaturalSkills.Dig;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Gambut extends GroundEngimon {
    public Gambut() {
        super();
        dialogue = "Taworan sama Boz Sendi";
        try {
            super.add_skill(new Dig());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
