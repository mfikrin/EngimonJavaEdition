package Entity.Engimon.Ground;

import Entity.Skill.Ground.NaturalSkills.Terroar;
import Exception.ElementNotSuitableException;
import Exception.SkillFullException;

public class Ratatouille extends GroundEngimon {
    public Ratatouille() {
        super();
        dialogue = "LINGUIIINIII~~~";
        try {
            super.add_skill(new Terroar());
        } catch (SkillFullException e) {
            e.printStackTrace();
        } catch (ElementNotSuitableException e) {
            e.printStackTrace();
        }
    }
}
