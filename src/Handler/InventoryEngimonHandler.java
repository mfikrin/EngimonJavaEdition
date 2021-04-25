package Handler;

import java.util.ArrayList;

import Entity.Inventory;
import Entity.SkillItem;
import Entity.Engimon.Engimon;

public class InventoryEngimonHandler {
    public static void sort_element(Inventory<Engimon> invent) {
        ArrayList<Engimon> al_engimon = invent.get_list();

        ArrayList<Engimon> fire_electric = new ArrayList<>();
        ArrayList<Engimon> water_ground = new ArrayList<>();
        ArrayList<Engimon> water_ice = new ArrayList<>();
        ArrayList<Engimon> fire = new ArrayList<>();
        ArrayList<Engimon> water = new ArrayList<>();
        ArrayList<Engimon> electric = new ArrayList<>();
        ArrayList<Engimon> ground = new ArrayList<>();
        ArrayList<Engimon> ice = new ArrayList<>();

        for(Engimon e : al_engimon) {
            if (e.is_fire() && e.is_electric()) {
                fire_electric.add(e);
            }else if (e.is_water() && e.is_ground()) {
                water_ground.add(e);
            }else if (e.is_water() && e.is_ice()) {
                water_ice.add(e);
            }else if (e.is_fire()) {
                fire.add(e);
            }else if (e.is_water()) {
                water.add(e);
            }else if (e.is_electric()) {
                electric.add(e);
            }else if (e.is_ground()) {
                ground.add(e);
            }else if (e.is_ice()) {
                ice.add(e);
            }
        }
        sort_level(fire_electric);
        sort_level(water_ground);
        sort_level(water_ice);
        sort_level(fire);
        sort_level(water);
        sort_level(electric);
        sort_level(ground);
        sort_level(ice);

        al_engimon.clear();
        al_engimon.addAll(fire_electric);
        al_engimon.addAll(water_ground);
        al_engimon.addAll(water_ice);
        al_engimon.addAll(fire);
        al_engimon.addAll(water);
        al_engimon.addAll(electric);
        al_engimon.addAll(ground);
        al_engimon.addAll(ice);

        invent.set_list(al_engimon);
    }

    private static void sort_level(ArrayList<Engimon> a) {
        for(int i = 0; i < a.size(); ++i) {
            for (int j = i + 1; j < a.size(); ++j) {
                if (a.get(j).get_level() > a.get(i).get_level()) {
                    Engimon temp = a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,temp);
                }
            }
        }
    }

    public static void sort_skillitem(Inventory<SkillItem> invent) {
        ArrayList<SkillItem> a = invent.get_list();

        for(int i = 0; i < a.size(); ++i) {
            for (int j = i + 1; j < a.size(); ++j) {
                if (a.get(j).get_nbpower() > a.get(i).get_nbpower()) {
                    SkillItem temp = a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,temp);
                }
            }
        }
    }
}
