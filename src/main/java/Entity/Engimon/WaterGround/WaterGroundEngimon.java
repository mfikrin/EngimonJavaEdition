package Entity.Engimon.WaterGround;

import Entity.Engimon.Engimon;

public class WaterGroundEngimon extends Engimon {
    public WaterGroundEngimon() {
        super();
    }

    public WaterGroundEngimon(WaterGroundEngimon e) {
        super(e);
    }

    @Override
    public boolean is_water(){
        return true;
    }
    
    @Override
    public boolean is_ground(){
        return true;
    }

    @Override
    public Engimon clone() {
        return new WaterGroundEngimon(this);
    }
}
