package Entity.Engimon.Fire;

import Entity.Skill.Fire.NaturalSkills.Cremation;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Compfer extends FireEngimon {
    public Compfer() {
        super();
        try {
            super.add_skill(new Cremation());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
