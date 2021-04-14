package src.Entity.Engimon.FireElectric;

import src.Entity.Skill.FireElectric.Plasma;

public class Laiter extends FireElectricEngimon{
    public Laiter() {
        super();
        super.add_skill(new Plasma());
    }
}
