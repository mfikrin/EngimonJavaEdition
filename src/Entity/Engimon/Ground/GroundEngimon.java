package src.Entity.Engimon.Ground;

import src.Entity.Engimon.Engimon;

public class GroundEngimon extends Engimon{

    public GroundEngimon(){

    }

    public GroundEngimon(String name){
        super(name);
    }

    @Override
    public boolean is_ground() {
        // TODO Auto-generated method stub
        return true;
    }
    
}