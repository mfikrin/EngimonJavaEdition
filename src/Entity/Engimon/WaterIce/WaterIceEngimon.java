package Entity.Engimon.WaterIce;

import Entity.Engimon.Engimon;

public class WaterIceEngimon extends Engimon {
    public WaterIceEngimon() {
        super();
    }

    @Override
    public boolean is_water(){
        return true;
    }

    @Override
    public boolean is_ice(){
        return true;
    }
}
