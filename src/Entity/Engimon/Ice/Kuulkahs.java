package src.Entity.Engimon.Ice;

import src.Entity.Skill.Ice.NaturalSkills.Cold;

public class Kuulkahs extends IceEngimon{
    public Kuulkahs() {
        super();
        super.add_skill(new Cold());
    }
}
