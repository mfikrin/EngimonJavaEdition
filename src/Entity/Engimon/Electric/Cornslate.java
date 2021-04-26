package Entity.Engimon.Electric;

import Entity.Skill.Electric.NaturalSkills.Volt;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Cornslate extends ElectricEngimon {
    public Cornslate() {
        super();
        dialogue = "!@#iqeuiw.... Kzzstst%#@..";
        try {
            super.add_skill(new Volt());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
