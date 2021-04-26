package Entity.Engimon.Ground;

import Entity.Engimon.Engimon;

public class GroundEngimon extends Engimon {

    public GroundEngimon() {
        super();
    }

    public GroundEngimon(GroundEngimon e) {
        super(e);
    }

    @Override
    public boolean is_ground() {
        return true;
    }

    @Override
    public Engimon clone() {
        return new GroundEngimon(this);
    }
    
}
