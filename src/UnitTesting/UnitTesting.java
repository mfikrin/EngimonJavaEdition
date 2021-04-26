package UnitTesting;

import Entity.Player;
import Entity.SkillItem;
import Entity.Engimon.Engimon;
import Entity.Skill.Skill;
import Exception.ElementNotSuitableException;
import Exception.InsufficientLevelException;
import Exception.InventoryFullException;
import Exception.SkillFullException;
import Generator.EngimonGenerator;
import Generator.SkillGenerator;

public class UnitTesting {

    public static void test() {
        test_one();
        test_two();
        test_three();
        test_four();
        test_five();
        test_six();
        test_seven();
        test_eight();
        test_nine();
        test_ten();
        test_eleven();
        test_twelve();
        test_thirteen();
        test_fourteen();
        test_fifteen();
        test_sixteen();
        test_seventeen();
        test_eighteen();
        test_nineteen();
        test_twenty();
        test_twentyone();
    }
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

    public static void test_fourteen() {
        Player p = new Player();
        SkillItem si = new SkillItem(SkillGenerator.generate_random_skill());
        while(si.get_quantity() < 100) {
            si.incr_quantity();
        }
        try {
            p.add_skill_item(si);
        } catch (InventoryFullException e) {
            assert false;
        }

        try {
            p.add_skill_item(si);
        } catch (InventoryFullException e) {
            assert true;
        }

        try {
            p.add_engimon(EngimonGenerator.generate_rand_engimon());
        } catch (InventoryFullException e) {
            assert true;
        }
    }

    public static void test_fifteen() {
        Player p = new Player();

        Engimon e1 = EngimonGenerator.generate_fire();
        Engimon e2 = EngimonGenerator.generate_electric();

        while(e1.get_level() < 10) {
            e1.add_exp(1);
        }

        while(e2.get_level() < 10) {
            e2.add_exp(1);
        }

        try {
            p.add_engimon(e1);
            p.add_engimon(e2);
        } catch (InventoryFullException e) {
            assert false;
        }

        try {
            p.breed(0, 1);
        } catch (InsufficientLevelException | SkillFullException | ElementNotSuitableException e) {
            assert false;
        }

        p.set_active_engimon(2);
        assert p.get_engimon().is_fire() && p.get_engimon().is_electric();
    }

    public static void test_sixteen() {
        Player p = new Player();

        Engimon e1 = EngimonGenerator.generate_water();
        Engimon e2 = EngimonGenerator.generate_ground();

        while(e1.get_level() < 10) {
            e1.add_exp(1);
        }

        while(e2.get_level() < 10) {
            e2.add_exp(1);
        }

        try {
            p.add_engimon(e1);
            p.add_engimon(e2);
        } catch (InventoryFullException e) {
            assert false;
        }

        try {
            p.breed(0, 1);
        } catch (InsufficientLevelException | SkillFullException | ElementNotSuitableException e) {
            assert false;
        }

        p.set_active_engimon(2);
        assert p.get_engimon().is_water() && p.get_engimon().is_ground();
    }

    public static void test_seventeen() {
        Player p = new Player();

        Engimon e1 = EngimonGenerator.generate_water();
        Engimon e2 = EngimonGenerator.generate_ice();

        while(e1.get_level() < 10) {
            e1.add_exp(1);
        }

        while(e2.get_level() < 10) {
            e2.add_exp(1);
        }

        try {
            p.add_engimon(e1);
            p.add_engimon(e2);
        } catch (InventoryFullException e) {
            assert false;
        }

        try {
            p.breed(0, 1);
        } catch (InsufficientLevelException | SkillFullException | ElementNotSuitableException e) {
            assert false;
        }

        p.set_active_engimon(2);
        assert p.get_engimon().is_water() && p.get_engimon().is_ice();
    }

    public static void test_eighteen() {
        Player p = new Player();

        Engimon e1 = EngimonGenerator.generate_water();
        Engimon e2 = EngimonGenerator.generate_fire();

        while(e1.get_level() < 10) {
            e1.add_exp(1);
        }

        while(e2.get_level() < 10) {
            e2.add_exp(1);
        }

        try {
            p.add_engimon(e1);
            p.add_engimon(e2);
        } catch (InventoryFullException e) {
            assert false;
        }

        try {
            p.breed(0, 1);
        } catch (InsufficientLevelException | SkillFullException | ElementNotSuitableException e) {
            assert false;
        }

        p.set_active_engimon(2);
        assert p.get_engimon().is_fire();
    }

    public static void test_nineteen() {
        Player p = new Player();

        Engimon e1 = EngimonGenerator.generate_water();
        Engimon e2 = EngimonGenerator.generate_electric();

        while(e1.get_level() < 10) {
            e1.add_exp(1);
        }

        while(e2.get_level() < 10) {
            e2.add_exp(1);
        }

        try {
            p.add_engimon(e1);
            p.add_engimon(e2);
        } catch (InventoryFullException e) {
            assert false;
        }

        try {
            p.breed(0, 1);
        } catch (InsufficientLevelException | SkillFullException | ElementNotSuitableException e) {
            assert false;
        }

        p.set_active_engimon(2);
        assert p.get_engimon().is_electric();
    }

    public static void test_twenty() {
        Player p = new Player();

        Engimon e1 = EngimonGenerator.generate_water();
        Engimon e2 = EngimonGenerator.generate_ground();

        try {
            p.add_engimon(e1);
            p.add_engimon(e2);
        } catch (InventoryFullException e) {
            assert false;
        }

        try {
            p.breed(0, 1);
        } catch (InsufficientLevelException | SkillFullException | ElementNotSuitableException e) {
            assert true;
        }
    }

    public static void test_twentyone() {
        Engimon e = EngimonGenerator.generate_fire();

        try {
            e.add_skill(SkillGenerator.generate_water());
        } catch (SkillFullException e1) {
            assert false;;
        } catch (ElementNotSuitableException e1) {
            assert true;
        }

        try {
            e.add_skill(SkillGenerator.generate_fire());
            e.add_skill(SkillGenerator.generate_fire());
            e.add_skill(SkillGenerator.generate_fire());
            e.add_skill(SkillGenerator.generate_fire());
        } catch (SkillFullException e1) {
            assert false;
        } catch (ElementNotSuitableException e1) {
            assert false;
        }

        try {
            e.add_skill(SkillGenerator.generate_fire());
        } catch (SkillFullException e1) {
            assert true;
        } catch (ElementNotSuitableException e1) {
            assert false;
        }
    }


}
