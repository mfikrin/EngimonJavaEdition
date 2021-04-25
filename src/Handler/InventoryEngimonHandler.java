package Handler;

import java.util.ArrayList;

import Entity.Inventory;
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
}
