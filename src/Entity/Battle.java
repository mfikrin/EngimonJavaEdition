package Entity;

import java.util.ArrayList;
import java.util.Collections;

import Entity.Engimon.Engimon;

public class Battle {
    private double power_engimon1;
    private double power_engimon2;
    private static final double[] advantage = {
        1 , 0 , 1 , 0.5 , 2 ,
        2 , 1 , 0 , 1 , 1 ,
        1 , 2 , 1 , 0 , 1.5 ,
        1.5 , 1 , 2 , 1 , 0,
        0 , 1 , 0.5 , 2 , 1
    };

    private static final int idx_fire = 0;
    private static final int idx_water = 1;
    private static final int idx_electric = 2;
    private static final int idx_ground = 3;
    private static final int idx_ice = 4;
    private static final int column = 5;


    public Battle(Engimon e1, Engimon e2) {
        ArrayList<Double> a_adv1 = new ArrayList<>();
        ArrayList<Double> a_adv2 = new ArrayList<>();

        int[] idx_e1 = {-1,-1};
        retrieve_adv_idx(idx_e1,e1);
        retrieve_adv_idx(idx_e1,e1);
        int[] idx_e2 = {-1,-1};
        retrieve_adv_idx(idx_e2,e2);
        retrieve_adv_idx(idx_e2,e2);

        for(int i : idx_e1) {
            for(int j : idx_e2) {
                a_adv1.add(retrieve_adv(i,j));
                a_adv2.add(retrieve_adv(j,i));
            }
        }

        double adv1 = Collections.max(a_adv1);
        double adv2 = Collections.max(a_adv2);

        power_engimon1 = (e1.get_level() * adv1) + e1.get_sum_skill_power();
        power_engimon2 = (e2.get_level() * adv2) + e2.get_sum_skill_power();
    }

    //Output first engimon's power level if i = 1 and
    //second engimon's power level if i = 2
    public double get_power_level(int i) {
        if (i == 1) {
            return power_engimon1;
        }

        if (i == 2) {
            return power_engimon2;
        }

        return -1;
    }

    private static int[] retrieve_adv_idx(int[] idx,Engimon e) {
        boolean condition = idx[0] == -1;

        if (e.is_fire()) {
            if (condition) {
                idx[0] = idx_fire;
            }else {
                if (idx[0] != idx_fire) {
                    idx[1] = idx_fire;
                }
            }
        }

        if (e.is_water()) {
            if (condition)
            {
                idx[0] = idx_water;
            }else {
                if (idx[0] != idx_water) {
                    idx[1] = idx_water;
                }
            }
        }

        if (e.is_electric()) {
            if (condition) {
                idx[0] = idx_electric;
            }else {
                if (idx[0] != idx_electric) {
                    idx[1] = idx_electric;
                }
            }
        }

        if (e.is_ground()) {
            if (condition) {
                idx[0] = idx_ground;
            }else {
                if (idx[0] != idx_ground) {
                    idx[1] = idx_ground;
                }
            }
        }

        if (e.is_ice()) {
            if (condition) {
                idx[0] = idx_ice;
            }else {
                if (idx[0] != idx_ice) {
                    idx[1] = idx_ice;
                }
            }
        }
        return idx;
    }

    private static double retrieve_adv(int this_engimon_idx, int enemy_engimon_idx) {
        if (this_engimon_idx != -1 && enemy_engimon_idx != -1) {
            int idx_adv = (column * this_engimon_idx) + enemy_engimon_idx;
            return advantage[idx_adv];
        }
        return -1;
    }
}
