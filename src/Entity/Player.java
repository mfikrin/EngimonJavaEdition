package Entity;

import Entity.Engimon.Engimon;
import Exception.ElementNotSuitableException;
import Exception.InsufficientLevelException;
import Exception.InventoryFullException;
import Exception.SkillFullException;
import Exception.ZeroQuantityException;
import Generator.Breeding;

public class Player {

    public static final int max_inv_capacity_player = 100;
    private Inventory<Engimon> i_engimon;
    private Inventory<SkillItem> i_skillitem;
    private Engimon active_engimon;
    private Position pos;
    private Position pos_engimon;

    //Constructor
    public Player() {
        pos = new Position(1,0);
        i_engimon = new Inventory<Engimon>();
        i_skillitem = new Inventory<SkillItem>();
    }

    //Getter
    public Inventory<Engimon> get_inventory_engimon(){
        return i_engimon;
    }
    public Inventory<SkillItem> get_inventory_skill_item(){
        return i_skillitem;
    }

    public Engimon get_engimon() {
        return active_engimon;
    }

    public Position get_pos() {
        return pos;
    }

    public Position get_engimon_pos() {
        return pos_engimon;
    }

    public int get_total_inventory_capacity() {
        int skillitemq = 0;
        int engimonq = i_engimon.size();
        for(int i = 0; i < i_skillitem.size(); ++i){
            skillitemq += i_skillitem.get_item(i).get_quantity();
        }
        return skillitemq + engimonq;
    }

    //Setter
    public void set_active_engimon(int i) {
        active_engimon = i_engimon.get_item(i);
        pos_engimon = active_engimon.get_pos();
    }

    public void add_engimon(Engimon e) throws InventoryFullException{
        if (get_total_inventory_capacity() < max_inv_capacity_player){
            i_engimon.add_item(e);
        }else{
            throw new InventoryFullException("Inventory has reached it's max capcity!", null);
        }
    }

    public void add_skill_item(SkillItem si) throws InventoryFullException {
        if (get_total_inventory_capacity() < max_inv_capacity_player){
            boolean found = false;
            for(int i = 0; i < i_skillitem.size(); ++i) {
                if (i_skillitem.get_item(i).get_name().equals(si.get_name())) {
                    found = true;
                    SkillItem chosen_si = i_skillitem.get_item(i);
                    chosen_si.incr_quantity();
                    //yang dibawah bisa dicomment kalau ternyata yg atas udh reference ke yg oinventory
                    i_skillitem.set_item(i, chosen_si);
                }
            }

            if (!found) {
                i_skillitem.add_item(si);
            }
        }else {
            throw new InventoryFullException("Inventory has reached it's max capcity!", null);
        }
    }

    public void free_engimon(int idx_engimon) {
        i_engimon.remove(idx_engimon);
    }

    public void rename_engimon(int idx_engimon, String new_name) {
        Engimon e = i_engimon.get_item(idx_engimon);
        e.set_name(new_name);
    }

    public void remove_skillitem(int idx, int quantity) {
        SkillItem si = i_skillitem.get_item(idx);
        for(int i = 0; i < quantity; ++i){
            si.decr_quantity();
        }
        if (si.get_quantity() == 0){
            i_skillitem.remove(idx);
        }
    }

    public void move(char input) {
        Position old_pos = new Position(pos);

        if(input == 'w') {
            pos.decr_y();
            pos_engimon = old_pos;
        }else if(input == 's') {
            pos.incr_y();
            pos_engimon = old_pos;
        }else if(input == 'a') {
            pos.decr_x();
            pos_engimon = old_pos;
        }else if(input == 'd') {
            pos.incr_x();
            pos_engimon = old_pos;
        }
    }

    public void breed(int idx_parent1, int idx_parent2) throws InsufficientLevelException, SkillFullException, ElementNotSuitableException
    {
        Engimon result = Breeding.breed(i_engimon.get_item(idx_parent1), i_engimon.get_item(idx_parent2));
        i_engimon.add_item(result);
    }

    public void teach(int idx_engimon, int idx_skillitem) throws ElementNotSuitableException, SkillFullException, ZeroQuantityException {
        SkillItem s_item = i_skillitem.get_item(idx_skillitem);
        Engimon learner = i_engimon.get_item(idx_engimon);
        s_item.learn(learner);
    }

    public void show_certain_engimon(int i) {
        //code goes here..
    }

    public void show_engimon_inventory() {
        //code goes here..
    }

    public void show_skillitem_inventory() {
        //code goes here..
    }

    public void show_commands() {
        //code goes here..
    }

    //battle dengan engimon yg adjacent (ketika battle tampilkan detail engimon lawan ke layar)

    //save game
}
