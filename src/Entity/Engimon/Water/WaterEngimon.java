package src.Entity.Engimon.Water;

import src.Entity.Engimon.Engimon;

public class WaterEngimon extends Engimon{

    public WaterEngimon(){

    }

    public WaterEngimon(String name){
        super(name);
    }

    @Override
    public boolean is_water() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
