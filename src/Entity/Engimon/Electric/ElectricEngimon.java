package Entity.Engimon.Electric;

import Entity.Engimon.Engimon;

public class ElectricEngimon extends Engimon {
    public ElectricEngimon() {
        super();
    }

    public ElectricEngimon(ElectricEngimon e) {
        super(e);
    }

    @Override
    public boolean is_electric() {
        return true;
    }

    @Override
    public Engimon clone() {
        return new ElectricEngimon(this);
    }
    
}
