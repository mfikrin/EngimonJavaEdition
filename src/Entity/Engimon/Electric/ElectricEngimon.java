package src.Entity.Engimon.Electric;

import src.Entity.Engimon.Engimon;

public class ElectricEngimon extends Engimon{

    public ElectricEngimon() {
        super();
    }

    public ElectricEngimon(String name) {
        super(name);
    }

    @Override
    public boolean is_electric() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
