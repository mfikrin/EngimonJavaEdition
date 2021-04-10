package src.Entity.Engimon.Electric;

import src.Entity.Skill.Electric.NaturalSkills.Charge;

public class Choolok extends ElectricEngimon{
    public Choolok() {
        super();
        super.add_skill(new Charge());
    }
}
