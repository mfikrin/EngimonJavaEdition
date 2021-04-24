package Entity.Engimon.FireElectric;

import Entity.Skill.FireElectric.Energya;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Azula extends FireElectricEngimon {
    public Azula() {
        super();
        try {
            super.add_skill(new Energya());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
