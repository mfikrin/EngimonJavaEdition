package src.Entity.Engimon.Electric;

import src.Entity.Skill.Electric.NaturalSkills.Volt;

public class Cornslate extends ElectricEngimon{
    public Cornslate() {
        super();
        super.add_skill(new Volt());
    }
}
