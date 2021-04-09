package src.Entity.Skill;

import src.Entity.Generator;
import src.Entity.Skill.Fire.*;
import src.Entity.Skill.Electric.*;
import src.Entity.Skill.Water.*;
import src.Entity.Skill.Ground.*;
import src.Entity.Skill.Ice.*;

public class SkillGenerator extends Generator{
    public static Skill generate_fire(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Rakab();
        }
        return null;
    }

    public static Skill generate_water(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Tsunami();
        }
        return null;
    }

    public static Skill generate_electric(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Stroom();
        }
        return null;
    }

    public static Skill generate_ground(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Tectonic();
        }
        return null;
    }

    public static Skill generate_ice(){
        int determine = rand(1,1);
        if (determine == 1){
            return new Breeze();
        }
        return null;
    }
}
