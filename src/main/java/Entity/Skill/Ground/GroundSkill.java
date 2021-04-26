package Entity.Skill.Ground;

import Entity.Skill.Skill;

public class GroundSkill extends Skill {
    public GroundSkill(){
        super();
    }

    public GroundSkill(GroundSkill other) {
        super(other);
    }

    @Override
    public Skill clone() {
        return new GroundSkill(this);
    }

    @Override
    public boolean is_ground() {
        return true;
    }
}
