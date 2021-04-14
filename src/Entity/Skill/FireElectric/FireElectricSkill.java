package src.Entity.Skill.FireElectricNatural;

import src.Entity.Skill.Skill;

public class FireElectricSkill extends Skill{
    public FireElectricSkill() {
        super();
    }
    public FireElectricSkill(FireElectricSkill other){
        super(other);
    }
    @Override
    public FireElectricSkill clone(){
        return new FireElectricSkill(this);
    }
    @Override
    public boolean is_fire(){
        return true;
    }
    @Override
    public boolean is_electric(){
        return true;
    }
}
