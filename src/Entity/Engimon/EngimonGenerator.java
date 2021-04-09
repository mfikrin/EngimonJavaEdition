package src.Entity.Engimon;

import src.Entity.Engimon.Fire.*;
import src.Entity.Engimon.Water.*;
import src.Entity.Engimon.Ground.*;
import src.Entity.Engimon.Electric.*;
import src.Entity.Engimon.Ice.*;

public class EngimonGenerator {
    public int rand(int min, int max){
        double resdouble = (Math.random() * ((max-min) + 1)) + min;
        return (int) resdouble;
    }
    public Engimon generate_fire(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Ashatee();
        }
        return null;
    }
    public Engimon generate_water(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Gurame();
        }
        return null;
    }
    public Engimon generate_ice(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Kuulkahs();
        }
        return null;
    }
    public Engimon generate_ground(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Ratatouille();
        }
        return null;
    }
    public Engimon generate_electric(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Peelen();
        }
        return null;
    }
}