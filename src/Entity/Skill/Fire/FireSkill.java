package Entity.Skill.Fire;

import Entity.Skill.Skill;

public class FireSkill extends Skill {
    public FireSkill() {
        super();
    }

    public FireSkill(FireSkill other) {
        super(other);
    }

    @Override
    public FireSkill clone() {
        return new FireSkill(this);
    }
    
    @Override
    public boolean is_fire() {
        return true;
    }
}
