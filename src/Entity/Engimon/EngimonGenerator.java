package src.Entity.Engimon;

import src.Entity.Engimon.Fire.*;
import src.Entity.Engimon.Water.*;
import src.Entity.Engimon.Ground.*;
import src.Entity.Generator;
import src.Entity.Engimon.Electric.*;
import src.Entity.Engimon.Ice.*;

public class EngimonGenerator extends Generator{
    public static Engimon generate_fire(){
        int determine = rand(1,5);
        if (determine == 1){
            return new Ashatee();
        }else if (determine == 2){
            return new Aapee();
        }else if (determine == 3){
            return new Compfer();
        }else if (determine == 4){
            return new Cowrake();
        }else if (determine == 5){
            return new Elpijee();
        }
        return null;
    }
    public static Engimon generate_water(){
        int determine = rand(1,5);
        if (determine == 1){
            return new Gurame();
        }else if (determine == 2){
            return new Baychec();
        }else if (determine == 3){
            return new Dolphzig();
        }else if (determine == 4){
            return new Hyushark();
        }else if (determine == 5){
            return new Pam();
        }
        return null;
    }
    public static Engimon generate_ice(){
        int determine = rand(1,5);
        if (determine == 1){
            return new Kuulkahs();
        }else if (determine == 2){
            return new AisKreem();
        }else if (determine == 3){
            return new Buckoo();
        }else if (determine == 4){
            return new Nytrogyn();
        }else if (determine == 5){
            return new Zerokelv();
        }
        return null;
    }
    public static Engimon generate_ground(){
        int determine = rand(1,5);
        if (determine == 1){
            return new Ratatouille();
        }else if (determine == 2){
            return new Airon();
        }else if (determine == 3){
            return new Gambut();
        }else if (determine == 4){
            return new Geongudud();
        }else if (determine == 5){
            return new Roc();
        }
        return null;
    }
    public static Engimon generate_electric(){
        int determine = rand(1,5);
        if (determine == 1){
            return new Peelen();
        }else if (determine == 2){
            return new Aroos();
        }else if (determine == 3){
            return new Choolok();
        }else if (determine == 4){
            return new Cornslate();
        }else if (determine == 5){
            return new Esidisi();
        }
        return null;
    }
}