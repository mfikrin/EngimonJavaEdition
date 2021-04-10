package src.Entity.Engimon.Electric;

import src.Entity.Skill.Electric.NaturalSkills.Electrons;

public class Aroos extends ElectricEngimon {
    public Aroos() {
        super();
        super.add_skill(new Electrons());
    }
}
