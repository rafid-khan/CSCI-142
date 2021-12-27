package game;

import heroes.Hero;

/**
 * The main class for the RPG game, Super Fantasy Hero Storm.  This class
 * deals with the main game playing.  The program is run on the command line
 * as:<br>
 * <br>
 * $ java HeroStorm dragon_seed_# lion_seed_#<br>
 * <br>
 * Here, the seeds are two integers used to seed the random number generators
 * when shuffling the two teams of 3 heroes.
 *
 * @author RIT CS
 * @author Rafid Khan
 */
public class HeroStorm {
    /**
     * The main method.  It checks the number of command line arguments,
     * then creates and plays the game.
     *
     * @param args the command line arguments, two integers for the dragon
     * and lion random number generator seeds
     */

    private int count;
    private final HeroParty lion;
    private final HeroParty dragon;

    public HeroStorm(int lionSeed, int dragonSeed) {
        this.lion = new HeroParty(Team.LION, lionSeed);
        this.dragon = new HeroParty(Team.DRAGON, dragonSeed);
        this.count = 1;
    }

    public void play() {
        /*
          The execution of the game
         */
        while (this.dragon.getHeroes().size() > 0 && this.lion.getHeroes().size() > 0) {
            System.out.println("Battle #" + this.count);
            System.out.println("==========");

            System.out.println(this.dragon.toString().toUpperCase() + ":");
            for (int i = 0; i < this.dragon.getHeroes().size(); i++) {
                System.out.println(this.dragon.getHeroes().get(i).toString());
            }
            System.out.println();

            System.out.println(this.lion.toString().toUpperCase() + ":");
            for (int i = 0; i < this.lion.getHeroes().size(); i++) {
                System.out.println(this.lion.getHeroes().get(i).toString());
            }
            System.out.println();

            if (this.count % 2 != 0) {
                System.out.println(new StringBuilder().append("*** ").append(this.dragon.getHeroes().get(0).getName()
                ).append(" vs ").append(this.lion.getHeroes().get(0).getName()).append("!"));
                System.out.println();
                Hero dragon_hero = this.dragon.getHeroes().get(0);
                Hero lion_hero = this.lion.getHeroes().get(0);
                this.dragon.removeHero();
                this.lion.removeHero();
                dragon_hero.attack(lion_hero);

                if (!(lion_hero.hasFallen())) {
                    lion_hero.attack(dragon_hero);
                    this.lion.addHero(lion_hero);
                }

                if (!(dragon_hero.hasFallen())) {
                    this.dragon.addHero(dragon_hero);
                }


            } else {

                System.out.println(new StringBuilder().append("*** ").append(this.lion.getHeroes().get(0).getName()
                ).append(" vs ").append(this.dragon.getHeroes().get(0).getName()).append("!"));
                System.out.println();
                Hero dragon_hero = this.dragon.getHeroes().get(0);
                Hero lion_hero = this.lion.getHeroes().get(0);
                this.dragon.removeHero();
                this.lion.removeHero();
                lion_hero.attack(dragon_hero);

                if (!(dragon_hero.hasFallen())) {
                    dragon_hero.attack(lion_hero);
                    this.dragon.addHero(dragon_hero);
                }
                if (!(lion_hero.hasFallen())) {
                    lion.addHero(lion_hero);
                }

            }
            this.count += 1;
            System.out.println();
        }

        if (this.lion.getHeroes().size() == 0) {
            System.out.println("Team Dragon wins!");
        } else {
            System.out.println("Team Lion wins!");
        }
    }

    public static void main(String[] args) {
        /*
          Main method that creates a HeroStorm instance and executes the game
         */
        if (args.length != 2) {
            System.out.println("Usage: java HeroStorm dragon# lion#");
        } else {
            HeroStorm start = new HeroStorm(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            start.play();
        }
    }
}

