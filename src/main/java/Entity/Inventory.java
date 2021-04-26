package Entity;

import java.util.ArrayList;

public class Inventory <T> {
    private ArrayList<T> inventory;

    public Inventory() {
        inventory = new ArrayList<T>();
    }

    public ArrayList<T> get_list() {
        return inventory;
    }

    public T get_item(int i) {
        return inventory.get(i);
    }

    public int size() {
        return inventory.size();
    }

    public void set_item(int idx, T item) {
        inventory.set(idx, item);
    }

    public void add_item(T item) {
        inventory.add(item);
    }

    public void remove(int i) {
        inventory.remove(i);
    }

    public void set_list(ArrayList<T> new_list) {
        inventory.clear();
        inventory = new_list;
    }

    public int get_index(T item){
        return inventory.indexOf(item);
    }
}