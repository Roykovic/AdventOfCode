package nl.roykovic.aoc._2022.day1_elf;

public class Elf implements Comparable<Elf>{
    private long calories = 0;

    public void addCalories(long calories){
        this.setCalories(getCalories() + calories);
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return String.valueOf(calories);
    }

    @Override
    public int compareTo(Elf otherElf) {
        return Long.compare(getCalories(), otherElf.getCalories());
    }
}
