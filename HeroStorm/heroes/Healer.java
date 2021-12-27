package heroes;

import game.Team;

public class Healer extends Hero{
    /**
    Our dependable medic healing class.
     This file creates the healer class and its methods
     */
    private static final int HEALER_HIT_POINTS = 35;
    private static final int DAMAGE_AMOUNT = 10;
    private static final int HEAL_AMOUNT = 10;

    public Healer(Team team){
        /*
        Creates a healer
         */
        super(Heroes.getName(team, Heroes.Role.HEALER), HEALER_HIT_POINTS);
    }

    public Heroes.Role getRole(){
        /*
        Returns the hero's role
         */
        return Heroes.Role.HEALER;
    }

    public void attack(Hero x){
        /*
        The healer's attacking method, which heals all of its team's heroes before
        attacking the opposition
         */
        this.heal(HEAL_AMOUNT);
        for (Hero y: this.getParty().getHeroes()){
            y.heal(HEAL_AMOUNT);
        }
        x.takeDamage(DAMAGE_AMOUNT);
    }
}
