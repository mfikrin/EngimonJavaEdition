package Entity.Engimon.Fire;

import Entity.Engimon.Engimon;

public class FireEngimon extends Engimon {
    public FireEngimon() {
        super();
    }

    public FireEngimon(FireEngimon e) {
        super(e);
    }
    
    @Override
    public boolean is_fire() {
        return true;
    }

    @Override
    public Engimon clone() {
        return new FireEngimon(this);
    }
}
