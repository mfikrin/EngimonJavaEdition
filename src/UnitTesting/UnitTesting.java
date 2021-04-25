package UnitTesting;

import Entity.Engimon.Engimon;
import Entity.Skill.Skill;
import Generator.EngimonGenerator;
import Generator.SkillGenerator;

public class UnitTesting {
    public static void test_one() {
        Engimon e = EngimonGenerator.generate_fire();
        assert e.is_fire();

        assert !e.is_water();

        assert !e.is_electric();

        assert !e.is_ground();

        assert !e.is_ice();

        assert e.get_skill_size() == 1;
    }
    
    public static void test_two() {
        Engimon e = EngimonGenerator.generate_water();
        assert e.is_water();

        assert !e.is_fire();

        assert !e.is_electric();

        assert !e.is_ground();

        assert !e.is_ice();

        assert e.get_skill_size() == 1;
    }

    public static void test_three() {
        Engimon e = EngimonGenerator.generate_ground();
        assert e.is_ground();

        assert !e.is_water();

        assert !e.is_electric();

        assert !e.is_fire();

        assert !e.is_ice();

        assert e.get_skill_size() == 1;
    }

    public static void test_four() {
        Engimon e = EngimonGenerator.generate_ice();
        assert e.is_ice();

        assert !e.is_water();

        assert !e.is_electric();

        assert !e.is_ground();

        assert !e.is_fire();

        assert e.get_skill_size() == 1;
    }

    public static void test_five() {
        Engimon e = EngimonGenerator.generate_electric();
        assert e.is_electric();

        assert !e.is_water();

        assert !e.is_fire();

        assert !e.is_ground();

        assert !e.is_ice();

        assert e.get_skill_size() == 1;
    }

    public static void test_six() {
        Engimon e = EngimonGenerator.generate_fireelectric();
        assert e.is_fire() && e.is_electric();

        assert !e.is_water();

        assert !e.is_ground();

        assert !e.is_ice();

        assert e.get_skill_size() == 1;
    }

    public static void test_seven() {
        Engimon e = EngimonGenerator.generate_waterground();
        assert e.is_water() && e.is_ground();

        assert !e.is_electric();

        assert !e.is_fire();

        assert !e.is_ice();

        assert e.get_skill_size() == 1;
    }

    public static void test_eight() {
        Engimon e = EngimonGenerator.generate_waterice();
        assert e.is_water() && e.is_ice();

        assert !e.is_electric();

        assert !e.is_ground();

        assert !e.is_fire();

        assert e.get_skill_size() == 1;
    }

    public static void test_nine() {
        Skill s = SkillGenerator.generate_fire();

        assert s.is_fire();

        assert !s.is_water();

        assert !s.is_electric();

        assert !s.is_ground();

        assert !s.is_ice();

        int i = 0;
        while (i < 5) {
            s.incr_mlevel();
            i++;
        }

        assert s.get_mlevel() == 3;
    }

    public static void test_ten() {
        Skill s = SkillGenerator.generate_water();

        assert s.is_water();

        assert !s.is_fire();

        assert !s.is_electric();

        assert !s.is_ground();

        assert !s.is_ice();

        int i = 0;
        while (i < 5) {
            s.incr_mlevel();
            i++;
        }

        assert s.get_mlevel() == 3;
    }

    public static void test_eleven() {
        Skill s = SkillGenerator.generate_electric();

        assert s.is_electric();

        assert !s.is_water();

        assert !s.is_fire();

        assert !s.is_ground();

        assert !s.is_ice();

        int i = 0;
        while (i < 5) {
            s.incr_mlevel();
            i++;
        }

        assert s.get_mlevel() == 3;
    }

    public static void test_twelve() {
        Skill s = SkillGenerator.generate_ground();

        assert s.is_ground();

        assert !s.is_water();

        assert !s.is_electric();

        assert !s.is_fire();

        assert !s.is_ice();

        int i = 0;
        while (i < 5) {
            s.incr_mlevel();
            i++;
        }

        assert s.get_mlevel() == 3;
    }

    public static void test_thirteen() {
        Skill s = SkillGenerator.generate_ice();

        assert s.is_ice();

        assert !s.is_water();

        assert !s.is_electric();

        assert !s.is_ground();

        assert !s.is_fire();

        int i = 0;
        while (i < 5) {
            s.incr_mlevel();
            i++;
        }

        assert s.get_mlevel() == 3;
    }


}
