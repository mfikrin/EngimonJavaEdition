package Entity.Engimon.Ice;

import Entity.Engimon.Engimon;

public class IceEngimon extends Engimon {
    public IceEngimon() {
        super();
    }

    public IceEngimon(IceEngimon e) {
        super(e);
    }

    @Override
    public boolean is_ice() {
        return true;
    }

    @Override
    public Engimon clone() {
        return new IceEngimon(this);
    }
}
