package nl.roykovic.AOC.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rucksack {
    private final Character[] firstCompartment;
    private final Character[] secondCompartment;

    public Rucksack(Character[] firstCompartment, Character[] secondCompartment) {
        this.firstCompartment = firstCompartment;
        this.secondCompartment = secondCompartment;
    }

    public Character[] sharedItems(){
        return Arrays.stream(firstCompartment).filter(c -> Arrays.asList(secondCompartment).contains(c)).distinct().toArray(Character[]::new);
    }

    public static int charToPrio(Character character){
        int hans = Character.getNumericValue(character);
        int frits = Character.isUpperCase(character) ? 38 : 96;
        return character - (Character.isUpperCase(character) ? 38 : 96);
    }

    @Override
    public String toString() {
        return "First: " + Arrays.toString(firstCompartment) + " Second: " + Arrays.toString(secondCompartment) + " Shared: " + Arrays.toString(sharedItems());
    }
}
