package Handler;
import java.util.ArrayList;

import Entity.Inventory;
import Entity.SkillItem;
public class InventorySkillItemHandler {
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
