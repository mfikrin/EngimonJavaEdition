package src.Entity;

import java.util.ArrayList;//mau make map bebas

public class Inventory <T>{
    private ArrayList<T> inventory;
    public Inventory(){
        inventory = new ArrayList<T>();
    }
    public void add_item(T item){
        inventory.add(item);
    }
    public T get_item(int i){
        return inventory.get(i);
    }
    public int size(){
        return inventory.size();
    }
}