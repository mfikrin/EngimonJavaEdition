package src.Entity.Engimon.Electric;

import src.Entity.Skill.Electric.NaturalSkills.Gamma;

public class Esidisi extends ElectricEngimon{
    public Esidisi() {
        super();
        super.add_skill(new Gamma());
    }
}
