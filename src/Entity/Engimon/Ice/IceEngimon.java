package src.Entity.Engimon.Ice;

import src.Entity.Engimon.Engimon;

public class IceEngimon extends Engimon{
    public IceEngimon(){

    }

    public IceEngimon(String name){
        super(name);
    }

    @Override
    public boolean is_ice() {
        // TODO Auto-generated method stub
        return true;
    }
}
