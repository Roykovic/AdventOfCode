package nl.roykovic.aoc._2015.day22_magicbossfight;

public class MagicAttack {
    private String name;
    private int cost;
    private int damage;
    private int heal;
    private int armor;
    private int regenerate;
    private int turns;

    public MagicAttack(String name, int cost, int damage, int heal, int armor, int regenerate, int turns) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.heal = heal;
        this.armor = armor;
        this.regenerate = regenerate;
        this.turns = turns;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getRegenerate() {
        return regenerate;
    }

    public void setRegenerate(int regenerate) {
        this.regenerate = regenerate;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
}
