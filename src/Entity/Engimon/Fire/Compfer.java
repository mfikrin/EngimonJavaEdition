package src.Entity.Engimon.Fire;

import src.Entity.Skill.Fire.NaturalSkills.Cremation;

public class Compfer extends FireEngimon{
    public Compfer() {
        super();
        super.add_skill(new Cremation());
    }
}
