package game;

import heroes.Hero;
import heroes.Heroes;
import heroes.Party;

import java.util.ArrayList;
import java.util.List;

/**
 * A party is a collection of non-fallen heroes that represents a team
 * The party is stored in a queue format when the two teams are facing each
 * other. This file creates the Party class and its methods.
 */
public class HeroParty implements heroes.Party {
    private final List<Hero> heroes;
    private final Team team;

    public HeroParty(Team team, int seed) {
        /*
          Creates the party, which will be used to hold our heroes into teams.
         */
        this.heroes = new ArrayList<Hero>();
        this.team = team;

        switch (seed) {
            case 0 -> {
                this.addHero(Hero.create(Heroes.Role.TANK, team, this));
                this.addHero(Hero.create(Heroes.Role.HEALER, team, this));
                this.addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
            }
            case 1 -> {
                this.addHero(Hero.create(Heroes.Role.HEALER, team, this));
                this.addHero(Hero.create(Heroes.Role.TANK, team, this));
                this.addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
            }
            case 2 -> {
                this.addHero(Hero.create(Heroes.Role.TANK, team, this));
                this.addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
                this.addHero(Hero.create(Heroes.Role.HEALER, team, this));
            }
            case 3 -> {
                this.addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
                this.addHero(Hero.create(Heroes.Role.HEALER, team, this));
                this.addHero(Hero.create(Heroes.Role.TANK, team, this));
            }
            case 5 -> {
                this.addHero(Hero.create(Heroes.Role.HEALER, team, this));
                this.addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
                this.addHero(Hero.create(Heroes.Role.TANK, team, this));
            }
            case 7 -> {
                this.addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
                this.addHero(Hero.create(Heroes.Role.TANK, team, this));
                this.addHero(Hero.create(Heroes.Role.HEALER, team, this));
            }
        }
    }

    public void addHero(Hero hero) {
        /*
          Adds a hero to the back of the collection (list)
         */
        this.getHeroes().add(hero);
    }

    public Hero removeHero() {
        /*
          Removes a hero at the front of the collection (list)
         */
        Hero x = this.heroes.get(0);
        this.heroes.remove(0);
        return x;
    }

    public int numHeroes() {
        /*
          Returns the number of heroes that are not fallen
         */
        return this.heroes.size();
    }

    public Team getTeam() {
        /*
          Returns the team that the party is associated with
         */
        return this.team;
    }

    public List<Hero> getHeroes() {
        /*
          Gets all the undefeated heroes in the party
         */
        return this.heroes;
    }

    public String toString() {
        /*
          Converts the party and its members to a string representation
         */
        if (this.team == Team.LION) {
            return "Lion";
        } else {
            return "Dragon";
        }
    }

}
