package src.Entity.Engimon.Fire;

import src.Entity.Skill.Fire.NaturalSkills.Burn;

public class Aapee extends FireEngimon{
    public Aapee() {
        super();
        super.add_skill(new Burn());
    }
}
