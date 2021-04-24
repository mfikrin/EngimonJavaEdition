package Entity.Engimon.Ice;

import Entity.Skill.Ice.NaturalSkills.Avalanche;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class AisKreem extends IceEngimon {
    public AisKreem() {
        super();
        try {
            super.add_skill(new Avalanche());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
