package src.Entity.Skill.Water;

import src.Entity.Skill.Skill;

public class WaterSkill extends Skill{
    public WaterSkill(){
        super();
    }
    @Override
    public boolean is_water() {
        return true;
    }
}
