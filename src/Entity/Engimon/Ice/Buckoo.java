package src.Entity.Engimon.Ice;

import src.Entity.Skill.Ice.NaturalSkills.Chill;

public class Buckoo extends IceEngimon{
    public Buckoo() {
        super();
        super.add_skill(new Chill());
    }
}
