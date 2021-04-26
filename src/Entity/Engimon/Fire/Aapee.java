package Entity.Engimon.Fire;

import Entity.Skill.Fire.NaturalSkills.Burn;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Aapee extends FireEngimon {
    public Aapee() {
        super();
        dialogue = "Flammmmmeee...";
        try {
            super.add_skill(new Burn());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
