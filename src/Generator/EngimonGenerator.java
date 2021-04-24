package Generator;

import Entity.Engimon.Fire.*;
import Entity.Engimon.FireElectric.*;
import Entity.Engimon.WaterGround.*;
import Entity.Engimon.WaterIce.*;
import Entity.Engimon.Water.*;
import Entity.Engimon.Ground.*;
import Entity.Engimon.Engimon;
import Entity.Engimon.Ice.*;
import Entity.Engimon.Electric.*;

public class EngimonGenerator extends Generator{

    public static Engimon generate_rand_engimon() {
        int determine = rand(1,8);
        
        if (determine == 1) {
            return generate_fire();
        }else if (determine == 2) {
            return generate_water();
        }else if (determine == 3) {
            return generate_electric();
        }else if (determine == 4) {
            return generate_ground();
        }else if (determine == 5) {
            return generate_ice();
        }else if (determine == 6) {
            return generate_fireelectric();
        }else if (determine == 7) {
            return generate_waterground();
        }else if (determine == 8) {
            return generate_waterice();
        }
        return null;
    }

    public static Engimon generate_fire() {
        int determine = rand(1,5);

        if (determine == 1) {
            return new Ashatee();
        }else if (determine == 2) {
            return new Aapee();
        }else if (determine == 3) {
            return new Compfer();
        }else if (determine == 4) {
            return new Cowrake();
        }else if (determine == 5) {
            return new Elpijee();
        }
        return null;
    }

    public static Engimon generate_water() {
        int determine = rand(1,5);

        if (determine == 1) {
            return new Gurame();
        }else if (determine == 2) {
            return new Baychec();
        }else if (determine == 3) {
            return new Dolphzig();
        }else if (determine == 4) {
            return new Hyushark();
        }else if (determine == 5) {
            return new Pam();
        }
        return null;
    }

    public static Engimon generate_ice() {
        int determine = rand(1,5);

        if (determine == 1) {
            return new Kuulkahs();
        }else if (determine == 2) {
            return new AisKreem();
        }else if (determine == 3) {
            return new Buckoo();
        }else if (determine == 4) {
            return new Nytrogyn();
        }else if (determine == 5) {
            return new Zerokelv();
        }
        return null;
    }

    public static Engimon generate_ground() {
        int determine = rand(1,5);
        if (determine == 1) {
            return new Ratatouille();
        }else if (determine == 2) {
            return new Airon();
        }else if (determine == 3) {
            return new Gambut();
        }else if (determine == 4) {
            return new Geongudud();
        }else if (determine == 5) {
            return new Roc();
        }
        return null;
    }

    public static Engimon generate_electric() {
        int determine = rand(1,5);
        if (determine == 1) {
            return new Peelen();
        }else if (determine == 2) {
            return new Aroos();
        }else if (determine == 3) {
            return new Choolok();
        }else if (determine == 4) {
            return new Cornslate();
        }else if (determine == 5) {
            return new Esidisi();
        }
        return null;
    }

    public static Engimon generate_fireelectric() {
        int determine = rand(1,2);
        if (determine == 1) {
            return new Azula();
        }else if(determine == 2) {
            return new Laiter();
        }
        return null;
    }

    public static Engimon generate_waterground() {
        int determine = rand(1,2);
        if(determine == 1) {
            return new Beloot();
        }else if(determine == 2) {
            return new Ogre();
        }
        return null;
    }

    public static Engimon generate_waterice() {
        int determine = rand(1,2);
        if(determine == 1) {
            return new Cumulo();
        }else if(determine == 2) {
            return new Strato();
        }
        return null;
    }
}