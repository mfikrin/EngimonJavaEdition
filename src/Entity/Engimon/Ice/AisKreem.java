package src.Entity.Engimon.Ice;

import src.Entity.Skill.Ice.NaturalSkills.Avalanche;

public class AisKreem extends IceEngimon{
    public AisKreem() {
        super();
        super.add_skill(new Avalanche());
    }
}
