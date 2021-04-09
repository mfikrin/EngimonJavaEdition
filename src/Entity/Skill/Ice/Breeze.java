package src.Entity.Skill.Ice;

import src.Entity.Skill.Skill;

public class Breeze extends Skill{
    public Breeze(){
        super();
        super.set_nbpower(150);
    }

    @Override
    public boolean is_ice() {
        return true;
    }
    
}
