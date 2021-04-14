package src.Entity.Engimon.FireElectric;

import src.Entity.Skill.FireElectric.Energya;

public class Azula extends FireElectricEngimon{
    public Azula() {
        super();
        super.add_skill(new Energya());
    }
}
