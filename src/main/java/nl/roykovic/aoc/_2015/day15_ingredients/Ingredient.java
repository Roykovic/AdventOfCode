package nl.roykovic.aoc._2015.day15_ingredients;

import java.util.List;

public class Ingredient {

    private String name;
    private int capacity;
    private int durability;
    private int flavor;
    private int texture;
    private int calories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getFlavor() {
        return flavor;
    }

    public void setFlavor(int flavor) {
        this.flavor = flavor;
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean scoreIsNotZero(int amount){
         int capacity = this.capacity * amount;
         int durability = this.durability * amount;
         int flavor = this.flavor * amount;
         int texture = this.texture * amount;
         int calories = this.calories * amount;

         return !List.of(capacity,durability,flavor,texture,calories).contains(0);
    }
}
