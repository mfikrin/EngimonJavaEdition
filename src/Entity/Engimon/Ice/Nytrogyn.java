package src.Entity.Engimon.Ice;

import src.Entity.Skill.Ice.NaturalSkills.Snow;

public class Nytrogyn extends IceEngimon{
    public Nytrogyn() {
        super();
        super.add_skill(new Snow());
    }
}
