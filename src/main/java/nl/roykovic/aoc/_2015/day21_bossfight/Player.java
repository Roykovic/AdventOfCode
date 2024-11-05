package nl.roykovic.aoc._2015.day21_bossfight;

public class Player {
    private int hitPoints;
    private int damage;
    private int armor;

    private int mana;

    public Player(int hitPoints,int mana) {
        this.hitPoints = hitPoints;
        this.mana = mana;
        this.armor = 0;
    }
    public Player(int hitPoints, int damage, int armor) {
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.armor = armor;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
