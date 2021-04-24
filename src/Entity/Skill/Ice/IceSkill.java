package Entity.Skill.Ice;

import Entity.Skill.Skill;

public class IceSkill extends Skill {
    public IceSkill(){
        super();
    }

    public IceSkill(IceSkill other) {
        super(other);
    }

    @Override
    public IceSkill clone() {
        return new IceSkill(this);
    }

    @Override
    public boolean is_ice() {
        return true;
    }

}
