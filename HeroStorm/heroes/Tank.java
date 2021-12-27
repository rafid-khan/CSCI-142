package heroes;

import game.Team;

public class Tank extends Hero{
    /**
     * Our ruggedly reliable damage sponge class.
     * This file creates the tank class and its methods
     */
    static final int TANK_HIT_POINTS = 40;
    private static final int DAMAGE_AMOUNT = 15;
    private static final double SHIELD_DMG_MULTIPLIER = 0.9;

    public Tank(Team team){
        /*
        Creates a tank
         */
        super(Heroes.getName(team, Heroes.Role.TANK), TANK_HIT_POINTS);
    }

    public Heroes.Role getRole(){
        /*
        Returns the hero's role
         */
        return Heroes.Role.TANK;
    }

    public void takeDamage(int x){
        /*
        The tank has a special ability which negates the damage it takes by
        10%
         */
        super.takeDamage((int)(x*SHIELD_DMG_MULTIPLIER));
    }

    public void attack(Hero x){
        /*
        The tank's attack method
         */
        x.takeDamage(DAMAGE_AMOUNT);
    }
}
