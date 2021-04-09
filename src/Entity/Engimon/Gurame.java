package src.Entity.Engimon;

public class Gurame extends Engimon{

    public Gurame(String name){
        super(name);
    }

    public Gurame() {
    }

    @Override
    public boolean is_fire() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean is_water() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean is_electric() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean is_ground() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean is_ice() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
