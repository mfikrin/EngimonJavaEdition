package Entity.Engimon.Fire;

import Entity.Skill.Fire.NaturalSkills.Scorch;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Elpijee extends FireEngimon {
    public Elpijee() {
        super();
        try {
            super.add_skill(new Scorch());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
