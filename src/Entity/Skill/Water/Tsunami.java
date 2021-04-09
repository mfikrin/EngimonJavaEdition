package src.Entity.Skill.Water;

import src.Entity.Skill.Skill;

public class Tsunami extends Skill{
    public Tsunami(){
        super();
        super.set_nbpower(150);
    }

    @Override
    public boolean is_water() {
        return true;
    }
    
}
