package Entity.Engimon.Fire;

import Entity.Skill.Fire.NaturalSkills.Molten;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Cowrake extends FireEngimon {
    public Cowrake() {
        super();
        dialogue = "RAKE RAKE RAKE!";
        try {
            super.add_skill(new Molten());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
