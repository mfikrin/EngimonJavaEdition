package src.Entity.Engimon.Fire;

import src.Entity.Skill.Fire.NaturalSkills.Molten;

public class Cowrake extends FireEngimon{
    public Cowrake(){
        super();
        super.add_skill(new Molten());
    }
}
