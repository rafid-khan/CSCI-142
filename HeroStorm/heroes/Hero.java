package heroes;

import game.Team;

public abstract class Hero {
    /**
     * This file creates an abstract class for our heroes
     */
    protected String name;
    protected int hitPoints;
    protected int maxHealth;
    private int attackPower;
    private Team team;
    private Party party;

    protected Hero(String name, int hitPoints){
        /*
        Creates a new hero (Protected so that only its subclasses
        can call it
         */
        this.name = name;
        this.hitPoints = hitPoints;
        this.maxHealth = hitPoints;
    }

    public abstract void attack(Hero enemy);

    public static Hero create(Heroes.Role role, Team team, Party party){
        /*
        Creates a hero of a specific role for a certain team.
         */
        Hero h;
        if (role == Heroes.Role.BERSERKER){
            h = new Berserker(team);
            h.attackPower = 20;
        }
        else if (role == Heroes.Role.HEALER){
            h = new Healer(team);
            h.attackPower = 10;
        }
        else{
            h = new Tank(team);
            h.attackPower = 15;
        }
        h.party = party;
        h.team = team;
        return h;
    }

    public Heroes.Role getRole(){
        /*
        Returns the hero's role
         */
        switch (this.name) {
            case "Trogdor":
            case "Simba":
                return Heroes.Role.BERSERKER;
            case "Elsa":
            case "Spyro":
                return Heroes.Role.HEALER;
            case "Smaug":
            case "Aslan":
                return Heroes.Role.TANK;
            default:
                System.out.println("Error");
                return null;
        }
    }

    public String getName(){
        /*
        Returns the hero's name
         */
        return this.name;
    }

    public Party getParty(){
        /*
        Returns the hero's party (team)
         */
        return party;
    }

    public void heal(int amount) {
        /*
        Heals a hero's health points by a given amount.
        The hero cannot restore more than their max health.
        It outputs a message to let the user know who got
        healed and how much
         */
        this.hitPoints += amount;
        if (this.hitPoints > maxHealth){
            this.hitPoints = maxHealth;
        }
        System.out.println(this.getName() + " heals " + amount + " points");
    }

    public void takeDamage(int amount){
        /*
        Method that takes health points away from a hero,
        but this value cannot exceed below zero.
         */
        this.hitPoints = this.hitPoints - amount;
        if (this.hitPoints < 0) {
            this.hitPoints = 0;
        }
        System.out.println(this.getName() + " takes " + amount + " damage");
    }

    public boolean hasFallen(){
        /*
        If a hero's health is 0 then they are considered fallen.
        This method returns whether they are fallen or not.
         */
        if(this.hitPoints <= 0){
            System.out.println(this.getName() + " has fallen!");
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        /*
        Returns a string representation of the hero in the the form:
        {name}, {ROLE}, #/# <- (health)
         */
        return this.getName() + ", " + this.getRole() + ", " + this.hitPoints + "/" + this.maxHealth;
    }
}
