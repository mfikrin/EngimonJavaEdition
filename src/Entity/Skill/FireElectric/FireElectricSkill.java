package Entity.Skill.FireElectric;

import Entity.Skill.Skill;

public class FireElectricSkill extends Skill {
    public FireElectricSkill() {
        super();
    }

    public FireElectricSkill(FireElectricSkill other) {
        super(other);
    }

    @Override
    public Skill clone() {
        return new FireElectricSkill(this);
    }

    @Override
    public boolean is_fire() {
        return true;
    }

    @Override
    public boolean is_electric() {
        return true;
    }
}
