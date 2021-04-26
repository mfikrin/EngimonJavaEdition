package SaveLoad;

import Entity.Player;
import Entity.Position;
import Entity.Engimon.Engimon;
import Entity.Engimon.Electric.*;
import Entity.Engimon.Fire.*;
import Entity.Engimon.Water.*;
import Entity.Engimon.Ground.*;
import Entity.Engimon.Ice.*;
import Entity.Engimon.WaterGround.*;
import Entity.Engimon.WaterIce.*;
import Entity.Engimon.FireElectric.*;
import Entity.Skill.Electric.*;
import Entity.Skill.Fire.*;
import Entity.Skill.Water.*;
import Entity.Skill.Ground.*;
import Entity.Skill.Ice.*;
import Entity.Skill.WaterGround.*;
import Entity.Skill.WaterIce.*;
import Entity.Skill.FireElectric.*;
import Entity.Skill.Electric.NaturalSkills.*;
import Entity.Skill.Fire.NaturalSkills.*;
import Entity.Skill.Water.NaturalSkills.*;
import Entity.Skill.Ground.NaturalSkills.*;
import Entity.Skill.Ice.NaturalSkills.*;


public class Loader {
    public static void load_player_pos(Player p, int x, int y) {
        Position pos = new Position(x,y);
        p.set_pos(pos);
    }

    public static void load_active_engimon_pos(Player p, int x, int y) {
        Position pos = new Position(x,y);
        p.set_engimon_pos(pos);
    }

    public static void load_engimon_pos(Engimon e, int x, int y) {
        Position pos = new Position(x,y);
        e.set_pos(pos);
    }

    public static Engimon load_engimon(String species) {
        switch(species) {
            case "Aroos":
                return new Aroos();
            case "Choolok":
                return new Choolok();
            case "Cornslate":
                return new Cornslate();
            case "Esidisi":
                return new Esidisi();
            case "Peelen":
                return new Peelen();
            case "Aapee":
                return new Aapee();
            case "Ashatee":
                return new Ashatee();
            case "Compfer":
                return new Compfer();
            case "Cowrake":
                return new Cowrake();
            case "Elpijee":
                return new Elpijee();
            case "Azula":
                return new Azula();
            case "Laiter":
                return new Laiter();
            case "Airon":
                return new Airon();
            case "Gambut":
                return new Gambut();
            case "Geongudud":
                return new Geongudud();
            case "Ratatouille":
                return new Ratatouille();
            case "Roc":
                return new Roc();
            case "AisKreem":
                return new AisKreem();
            case "Buckoo":
                return new Buckoo();
            case "Kuulkahs":
                return new Kuulkahs();
            case "Nytrogyn":
                return new Nytrogyn();
            case "Zerokelv":
                return new Zerokelv();
            case "Baychec":
                return new Baychec();
            case "Dolphzig":
                return new Dolphzig();
            case "Gurame":
                return new Gurame();
            case "Hyushark":
                return new Hyushark();
            case "Pam":
                return new Pam();
            case "Beloot":
                return new Beloot();
            case "Ogre":
                return new Ogre();
            case "Cumulo":
                return new Cumulo();
            case "Strato":
                return new Strato();
            default:
                return null;
        }
    }

    public static Skill load_skill(String skill_name) {
        switch(skill_name) {
            case "Charge":
                return new Charge();
        }
    }

    
}
