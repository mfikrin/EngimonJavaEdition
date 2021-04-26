package Entity.Skill.Water;

import Entity.Skill.Skill;

public class WaterSkill extends Skill {
    public WaterSkill() {
        super();
    }

    public WaterSkill(WaterSkill other) {
        super(other);
    }

    @Override
    public Skill clone() {
        return new WaterSkill(this);
    }
    
    @Override
    public boolean is_water() {
        return true;
    }
}
