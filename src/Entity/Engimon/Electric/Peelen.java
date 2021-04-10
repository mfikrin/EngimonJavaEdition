package src.Entity.Engimon.Electric;

import src.Entity.Skill.Electric.NaturalSkills.Thunderbolt;

public class Peelen extends ElectricEngimon {
    public Peelen() {
        super();
        super.add_skill(new Thunderbolt());
    }
}
