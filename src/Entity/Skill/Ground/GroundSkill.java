package src.Entity.Skill.Ground;

import src.Entity.Skill.Skill;

public class GroundSkill extends Skill{
    public GroundSkill(){
        super();
    }
    @Override
    public boolean is_ground(){
        return true;
    }
}
