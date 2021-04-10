package src.Entity.Engimon.Ice;

import src.Entity.Skill.Ice.NaturalSkills.Sublimation;

public class Zerokelv extends IceEngimon{
    public Zerokelv() {
        super();
        super.add_skill(new Sublimation());
    }
}
