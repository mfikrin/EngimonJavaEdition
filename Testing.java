import java.util.ArrayList;

import src.Entity.Engimon.Engimon;
import src.Entity.Engimon.EngimonGenerator;
import src.Entity.Skill.Skill;
import src.Entity.Skill.Electric.ElectricSkill;
// import src.Entity.Engimon.Fire.Ashatee;
// import src.Entity.Engimon.Ground.Ratatouille;
// import src.Entity.Engimon.Water.Gurame;
import src.Entity.Skill.Electric.NaturalSkills.Charge;

public class Testing {
    public static void main(String[] args){
        // ArrayList<Engimon> list = new ArrayList<>();
        // Engimon satu = EngimonGenerator.generate_fire();
        // Engimon dua = EngimonGenerator.generate_water();
        // Engimon tiga = EngimonGenerator.generate_ice();
        // Engimon empat = EngimonGenerator.generate_ground();
        // Engimon lima = EngimonGenerator.generate_electric();
        // list.add(satu);
        // list.add(dua);
        // list.add(tiga);
        // list.add(empat);
        // list.add(lima);
        // for (Engimon engimon : list) {
        //     System.out.println(engimon.get_species());
        //     System.out.println(engimon.is_ice());
        // }
        Skill testing = new Charge();
        Skill kedua = testing.clone();
        testing.set_name("name");
        System.out.println(kedua.is_electric());
        System.out.println(kedua.get_name());
        System.out.println(kedua.get_nbpower());
    }
}