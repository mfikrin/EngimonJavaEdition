package Entity.Engimon.FireElectric;

import Entity.Skill.FireElectric.Plasma;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Laiter extends FireElectricEngimon {
    public Laiter() {
        super();
        dialogue = "Kzzssttt....";
        try {
            super.add_skill(new Plasma());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
