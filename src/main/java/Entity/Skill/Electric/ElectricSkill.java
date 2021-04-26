package Entity.Skill.Electric;

import Entity.Skill.Skill;

public class ElectricSkill extends Skill {
    public ElectricSkill() {
        super();
    }

    public ElectricSkill(ElectricSkill other) {
        super(other);
    }

    @Override
    public Skill clone() {
        return new ElectricSkill(this);
    }

    @Override
    public boolean is_electric() {
        return true;
    }

}
