package src.Entity.Engimon.FireElectric;

import src.Entity.Engimon.Engimon;

public class FireElectricEngimon extends Engimon{
    public FireElectricEngimon() {
        super();
    }
    @Override
    public boolean is_fire(){
        return true;
    }
    @Override
    public boolean is_electric(){
        return true;
    }
    
}
