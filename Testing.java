import java.util.ArrayList;

import src.Entity.Engimon.Engimon;
import src.Entity.Engimon.EngimonGenerator;
import src.Entity.Engimon.Fire.Ashatee;
import src.Entity.Engimon.Ground.Ratatouille;
import src.Entity.Engimon.Water.Gurame;

public class Testing {
    public static void main(String[] args){
        ArrayList<Engimon> list = new ArrayList<>();
        Gurame gurame = new Gurame();
        Ashatee ashatee = new Ashatee();
        Ratatouille kunyit = new Ratatouille();

        list.add(gurame);
        list.add(ashatee);
        list.add(kunyit);

        Engimon test = list.get(0);
        test.set_name("megalodon");

        for(Engimon i : list){
            System.out.println(i.get_name());
        }

        double rand = Math.random();
        System.out.println(rand);

        Engimon random = EngimonGenerator.generate_fire();
        System.out.println(random.is_fire());
    }
}