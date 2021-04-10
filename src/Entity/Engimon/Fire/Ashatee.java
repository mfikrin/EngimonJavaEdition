package src.Entity.Engimon.Fire;

import src.Entity.Skill.Fire.NaturalSkills.Combustion;

public class Ashatee extends FireEngimon{
    public Ashatee() {
        super();
        super.add_skill(new Combustion());
    }
}
