package Generator;

import java.util.ArrayList;

import Entity.Engimon.Engimon;
import Entity.Skill.Skill;
import Exception.ElementNotSuitableException;
import Exception.InsufficientLevelException;
import Exception.SkillFullException;

public class Breeding {
    public static Engimon breed(Engimon parent1, Engimon parent2) throws InsufficientLevelException, SkillFullException, ElementNotSuitableException {
        Engimon result = null;

        if (parent1.get_level() < 4 || parent2.get_level() < 4){
            throw new InsufficientLevelException("Insufficient level!, Your engimon must be level 4 or more", null);
        }


        if((parent1.is_fire() && parent2.is_electric()) || (parent1.is_electric() && parent2.is_fire())){
            result = EngimonGenerator.generate_fireelectric();
        }else if((parent1.is_water() && parent2.is_ice()) || (parent1.is_ice() && parent2.is_water())){
            result = EngimonGenerator.generate_waterice();
        }else if((parent1.is_water() && parent2.is_ground()) || (parent1.is_ground() && parent2.is_water())){
            result = EngimonGenerator.generate_waterground();
        }else if ((parent1.is_fire() && parent2.is_water()) || (parent1.is_water() && parent2.is_fire())){
            result = EngimonGenerator.generate_water();
        }else if ((parent1.is_fire() && parent2.is_ground()) || (parent1.is_ground() && parent2.is_fire())){
            result = EngimonGenerator.generate_ground();
        }else if((parent1.is_fire() && parent2.is_ice()) || (parent1.is_ice() && parent2.is_fire())){
            result = EngimonGenerator.generate_fire();
        }else if((parent1.is_water() && parent2.is_electric()) || (parent1.is_electric() && parent2.is_water())){
            result = EngimonGenerator.generate_electric();
        }else if((parent1.is_electric() && parent2.is_ground()) || (parent1.is_ground() || parent2.is_electric())){
            result = EngimonGenerator.generate_ground();
        }else if((parent1.is_electric() && parent2.is_ice()) || (parent1.is_ice() && parent2.is_electric())){
            result = EngimonGenerator.generate_electric();
        }else if((parent1.is_ground() && parent2.is_ice()) || (parent1.is_ice() && parent2.is_ground())){
            result = EngimonGenerator.generate_ice();
        }
        else if(parent1.is_same_element(parent2)){
            if (parent1.is_fire()){
                result = EngimonGenerator.generate_fire();
            }else if(parent1.is_electric()){
                result = EngimonGenerator.generate_electric();
            }else if(parent1.is_ground()){
                result = EngimonGenerator.generate_ground();
            }else if(parent1.is_ice()){
                result = EngimonGenerator.generate_ice();
            }else if(parent1.is_water()){
                result = EngimonGenerator.generate_water();
            }
        }


        if (result != null){
            result.set_parent(parent1.get_name(), parent1.get_species(), parent2.get_name(), parent2.get_species());
            ArrayList<Skill> ref_skill_parent1 = parent1.get_all_skill();
            ArrayList<Skill> ref_skill_parent2 = parent2.get_all_skill();
            manage_skill(ref_skill_parent1, ref_skill_parent2, result);
            while(parent1.get_level() > 3){
                parent1.level_down();
            }
            while(parent2.get_level() > 3){
                parent2.level_down();
            }
        }
        return result;
    }

    public static void manage_skill(ArrayList<Skill> list1, ArrayList<Skill> list2, Engimon e) throws SkillFullException, ElementNotSuitableException {
        ArrayList<Skill>result = new ArrayList<>();

        filter_skill(list1, e);
        filter_skill(list2, e);

        for(Skill iter1 : list1) {
            int idx_list2_iter1 = is_exist_skill(list2, iter1);

            if (idx_list2_iter1 == -1) {
                result.add(iter1.clone());
            }else{
                if (iter1.get_mlevel() > list2.get(idx_list2_iter1).get_mlevel()){
                    list2.remove(idx_list2_iter1);
                    result.add(iter1.clone());
                }else if (iter1.get_mlevel() == list2.get(idx_list2_iter1).get_mlevel()){
                    Skill new_skill = iter1.clone();

                    list2.remove(idx_list2_iter1);
                    new_skill.incr_mlevel();
                    result.add(new_skill);
                }
            }
        }

        for(Skill iter2 : list2){
            result.add(iter2.clone());
        }

        sort_mlevel(result);

        int i = 0;
        while(e.get_skill_size() < 4 && i < result.size()) {
            e.add_skill(result.get(i));
            i++;
        }
    }
    
    public static void filter_skill(ArrayList<Skill> list, Engimon e) {
        ArrayList<Skill> found = new ArrayList<>();

        for(Skill skill : list) {
            if (e.is_skill_exist(skill.get_name()) || !e.is_same_element(skill)) {
                found.add(skill);
            }
        }

        list.removeAll(found);
    }

    public static int is_exist_skill(ArrayList<Skill> list, Skill s) {
        for(int i = 0; i < list.size(); ++i) {
            if (list.get(i).get_name().equals(s.get_name())) {
                return i;
            }
        }
        return -1;
    }

    public static void sort_mlevel(ArrayList<Skill> list) {
        for (int i = 0; i < list.size(); ++i) {
            for (int j = i+1; j < list.size(); ++j){
                if (list.get(i).get_mlevel() < list.get(j).get_mlevel()) {
                    Skill temp = list.get(i);
                    
                    list.set(i,list.get(j));
                    list.set(j,temp);
                }
            }
        }
    }
}
