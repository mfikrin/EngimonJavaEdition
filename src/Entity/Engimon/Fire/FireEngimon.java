package src.Entity.Engimon.Fire;

import src.Entity.Engimon.Engimon;

public class FireEngimon extends Engimon{
    public FireEngimon(){

    }
    public FireEngimon(String name){
        super(name);
    }
    @Override
    public boolean is_fire() {
        // TODO Auto-generated method stub
        return true;
    }
}
