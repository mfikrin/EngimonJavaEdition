package src.Entity.Skill.Electric;

import src.Entity.Skill.Skill;

public class ElectricSkill extends Skill{
    public ElectricSkill(){
        super();
    }

    @Override
    public boolean is_electric() {
        return true;
    }

}
