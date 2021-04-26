package Entity.Engimon.Fire;

import Entity.Skill.Fire.NaturalSkills.Combustion;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Ashatee extends FireEngimon {
    public Ashatee() {
        super();
        dialogue = "Ssshhhh.... Shaaateeee...";
        try {
            super.add_skill(new Combustion());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
