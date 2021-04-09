package src.Entity.Engimon.FireElectric;

import src.Entity.Engimon.Engimon;

public class FireElectricEngimon extends Engimon{
    public FireElectricEngimon() {
        super();
    }
    public FireElectricEngimon(String name){
        super(name);
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
