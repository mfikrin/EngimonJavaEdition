package src.Entity;

import src.Entity.Engimon.Engimon;

public class Player {
    //inventory (engimon,skill item)
    //active engimon
    private Inventory<Engimon> i_engimon;
    private Inventory<SkillItem> i_skillitem;
    private Engimon active_engimon;
    private Position pos;
    private Position pos_engimon;

    public Player(){
        pos = new Position(1,0);
        pos_engimon = new Position(0,0);
        i_engimon = new Inventory<Engimon>();
        i_skillitem = new Inventory<SkillItem>();
    }
    
    public void set_active_engimon(int i){
        active_engimon = i_engimon.get_item(i);
        //harusnya sih ini reference ya, jadi upated langsung ga perlu delete2
        //udah liat di internet katanya klo get sih returnnya reference
    }

    public Engimon get_engimon(){
        return active_engimon;
    }

    public void move(char input){
        //masalah decr incr bisa disesuaikan dengan guinya nanti mapnya gmn2
        if(input == 'w'){
            pos.decr_y();
            pos_engimon.incr_y();
        }else if(input == 's'){
            pos.incr_y();
            pos_engimon.decr_y();
        }else if(input == 'a'){
            pos.decr_x();
            pos_engimon.decr_x();
        }else if(input == 'd'){
            pos.incr_x();
            pos_engimon.incr_x();
        }
    }

    public void show_certain_engimon(int i){
        Engimon showed = i_engimon.get_item(i);
        //ngapain dah tuh
    }

    public void show_engimon_inventory(){
        //show inventory of engimon
    }

    public void show_skillitem_inventory(){
        //show inventory of skillitem
    }

    public int get_total_inventory_capacity(){
        int skillitemq = 0;
        int engimonq = i_engimon.size();
        for(int i = 0; i < i_skillitem.size(); ++i){
            skillitemq += i_skillitem.get_item(i).get_quantity();
        }
        return skillitemq + engimonq;
    }

    public boolean teach(int idx_engimon, int idx_skillitem){
        return i_skillitem.get_item(idx_skillitem).learn(i_engimon.get_item(idx_engimon));
    }

    public boolean breed(int engimon1, int engimon2){
        Engimon parent1 = i_engimon.get_item(engimon1);
        Engimon parent2 = i_engimon.get_item(engimon2);
        if (parent1.get_level() < 4 || parent2.get_level() < 4){
            return false;
        }
        if((parent1.is_fire() && parent2.is_electric()) || (parent1.is_electric() && parent2.is_fire())){

        }else if()
    }

    //nampilin list command
    //move
    //show data lengkap engimon di invent
    // dan setiap attr kelas dan skillnya
    // dan tampilin nama parent dan spesies parent
    
    //cek dan ganti active engimon
    //nampilin list skill item dimiliki
    // beserta info atribut skill tersebut (num base pow, dan elemennya)

    //menggunakan skill item pada engimon
    //breeding dua engimon
    //battle dengan engimon yg adjacent (ketika battle tampilkan detail engimon lawan ke layar)

    //membuang X amount of skill item dan melepas engimon dari inventory
    //change name engimon
    //save game
}
