package src.Entity.Engimon.Fire;

import src.Entity.Skill.Fire.NaturalSkills.Scorch;

public class Elpijee extends FireEngimon{
    public Elpijee() {
        super();
        super.add_skill(new Scorch());
    }
}
