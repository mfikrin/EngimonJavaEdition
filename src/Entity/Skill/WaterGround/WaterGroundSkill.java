package src.Entity.Skill.WaterGround;

import src.Entity.Skill.Skill;

public class WaterGroundSkill extends Skill{
    public WaterGroundSkill() {
        super();
    }
    public WaterGroundSkill(WaterGroundSkill other){
        super(other);
    }
    @Override
    public WaterGroundSkill clone(){
        return new WaterGroundSkill(this);
    }
    @Override
    public boolean is_water(){
        return true;
    }
    @Override
    public boolean is_ground(){
        return true;
    }
}
