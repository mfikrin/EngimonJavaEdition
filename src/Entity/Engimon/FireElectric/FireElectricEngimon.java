package Entity.Engimon.FireElectric;

import Entity.Engimon.Engimon;

public class FireElectricEngimon extends Engimon {
    public FireElectricEngimon() {
        super();
    }

    public FireElectricEngimon(FireElectricEngimon e) {
        super(e);
    }
    
    @Override
    public boolean is_fire() {
        return true;
    }
    
    @Override
    public boolean is_electric() {
        return true;
    }

    @Override
    public Engimon clone() {
        return new FireElectricEngimon(this);
    }
    
}
