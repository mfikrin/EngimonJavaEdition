package src.Entity.Skill.Ground;

import src.Entity.Skill.Skill;

public class Tectonic extends Skill{
    public Tectonic(){
        super();
        super.set_nbpower(150);
    }

    @Override
    public boolean is_ground(){
        return true;
    }
}
