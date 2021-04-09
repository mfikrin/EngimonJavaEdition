import java.util.ArrayList;

import src.Entity.Engimon.Engimon;
import src.Entity.Engimon.Fire.Ashatee;
import src.Entity.Engimon.Ground.Ratatouille;
import src.Entity.Engimon.Water.Gurame;

public class Testing {
    public static void main(String[] args) {
        ArrayList<Engimon> list = new ArrayList<>();
        Gurame gurame = new Gurame();
        Ashatee ashatee = new Ashatee("budi");
        Ratatouille kunyit = new Ratatouille();

        list.add(gurame);
        list.add(ashatee);
        list.add(kunyit);

        for(Engimon i : list){
            System.out.println(i.get_name());
            System.out.println(i.is_ground());
        }
    }
}
