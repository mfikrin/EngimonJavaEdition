package Generator;

import Entity.Skill.Fire.*;
import Entity.Skill.Skill;
import Entity.Skill.Electric.*;
import Entity.Skill.Water.*;
import Entity.Skill.Ground.*;
import Entity.Skill.Ice.*;

public class SkillGenerator extends Generator{
    public static Skill generate_random_skill() {
        int determine = rand(1,5);

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
        }
        return null;
    }
    public static Skill generate_fire() {
        int determine = rand(0,100);

        if (determine <= 30) {
            return new Rakab();
        }else if (determine <= 55) {
            return new Incinerate();
        }else if (determine <= 75) {
            return new Burst();
        }else if (determine <= 90) {
            return new Melt();
        }else if (determine <= 100) {
            return new Detonate();
        }
        return null;
    }

    public static Skill generate_water() {
        int determine = rand(0,100);

        if (determine <= 30) {
            return new Flood();
        }else if (determine <= 55) {
            return new Evaporate();
        }else if (determine <= 75) {
            return new Dissolve();
        }else if (determine <= 90) {
            return new Surf();
        }else if (determine <= 100) {
            return new Tsunami();
        }
        return null;
    }

    public static Skill generate_electric() {
        int determine = rand(0,100);

        if (determine <= 30) {
            return new Stroom();
        }else if (determine <= 55) {
            return new Zap();
        }else if (determine <= 75) {
            return new Blackout();
        }else if (determine <= 90) {
            return new Paralyze();
        }else if (determine <= 100) {
            return new Storm();
        }
        return null;
    }

    public static Skill generate_ground() {
        int determine = rand(0,100);

        if (determine <= 30) {
            return new Tectonic();
        }else if (determine <= 55) {
            return new Rumble();
        }else if (determine <= 75) {
            return new Magnitude();
        }else if (determine <= 90) {
            return new Errosion();
        }else if (determine <= 100) {
            return new Quake();
        }
        return null;
    }

    public static Skill generate_ice() {
        int determine = rand(0,100);

        if (determine <= 30) {
            return new Breeze();
        }else if (determine <= 55) {
            return new Crystalize();
        }else if (determine <= 75) {
            return new Hypotermia();
        }else if (determine <= 90) {
            return new Freeze();
        }else if (determine <= 100) {
            return new Blizzard();
        }
        return null;
    }
}
