package Entity.Engimon.Water;

import Entity.Engimon.Engimon;

public class WaterEngimon extends Engimon{

    public WaterEngimon() {
        super();
    }

    public WaterEngimon(WaterEngimon e) {
        super(e);
    }
    
    @Override
    public boolean is_water() {
        return true;
    }

    @Override
    public Engimon clone() {
        return new WaterEngimon(this);
    }
    
}
