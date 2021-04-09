package src.Entity.Skill.Electric;

import src.Entity.Skill.Skill;

public class Stroom extends Skill{

    public Stroom() {
        super();
        super.set_nbpower(150);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean is_electric() {
        return true;
    }
    
}
