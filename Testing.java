import java.util.ArrayList;

import src.Entity.Engimon.Ashatee;
import src.Entity.Engimon.Engimon;
import src.Entity.Engimon.Gurame;

public class Testing {
    public static void main(String[] args) {
        ArrayList<Engimon> list = new ArrayList<>();
        Gurame gurame = new Gurame();
        Ashatee ashatee = new Ashatee("budi");

        list.add(gurame);
        list.add(ashatee);

        Engimon dapet = list.get(0);
        System.out.println(dapet.get_name());
    }
}
