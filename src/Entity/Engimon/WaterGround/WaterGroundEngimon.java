package src.Entity.Engimon.WaterGround;

import src.Entity.Engimon.Engimon;

public class WaterGroundEngimon extends Engimon{
    public WaterGroundEngimon() {
        super();
    }
    public WaterGroundEngimon(String name){
        super(name);
    }
    @Override
    public boolean is_water(){
        return true;
    }
    @Override
    public boolean is_ground(){
        return true;
    }
}
