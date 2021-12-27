package heroes;

import game.Team;

public class Berserker extends Hero {
    /**
     * Our glass cannon damage class, the berserker.
     * This file creates the berserker class, and its methods
     */
    private static final int BERSERKER_HIT_POINTS = 30;
    private static final int DAMAGE_AMOUNT = 20;

    protected Berserker(Team team){
        /*
        Creates a berserker
         */
        super(Heroes.getName(team, Heroes.Role.BERSERKER), BERSERKER_HIT_POINTS);
    }

    public Heroes.Role getRole(){
        /*
        Returns the hero's role
         */
        return Heroes.Role.BERSERKER;
    }

    public void attack(Hero x){
        /*
        The berserker's attack method
         */
        x.takeDamage(DAMAGE_AMOUNT);
    }

    public String getName(){
        /*
        Returns the name of the hero
        Side note: I'm not sure why, but I needed to implement getName only for berserker
        and not for any of the other hero classes b/c of inheritance
        If I didn't there was an error.
         */
        return super.getName();
    }

}
