package Entity.Skill.WaterIce;

import Entity.Skill.Skill;

public class WaterIceSkill extends Skill {
    public WaterIceSkill() {
        super();
    }

    public WaterIceSkill(WaterIceSkill other) {
        super(other);
    }

    @Override
    public Skill clone() {
        return new WaterIceSkill(this);
    }

    @Override
    public boolean is_water() {
        return true;
    }

    @Override
    public boolean is_ice() {
        return true;
    }
}
