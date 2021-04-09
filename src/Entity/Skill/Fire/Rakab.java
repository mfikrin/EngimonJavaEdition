package src.Entity.Skill.Fire;

import src.Entity.Skill.Skill;

public class Rakab extends Skill{
    public Rakab(){
        super();
        super.set_nbpower(150);
    }

    @Override
    public boolean is_fire() {
        return true;
    }
}
