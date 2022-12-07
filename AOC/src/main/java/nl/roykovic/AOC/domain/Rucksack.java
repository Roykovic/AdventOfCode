package nl.roykovic.AOC.domain;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

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

    public Character[] sharedItems(Character[] otherCompartments){
        return Arrays
                .stream(ArrayUtils.addAll(firstCompartment, secondCompartment))
                .filter(c -> Arrays.asList(otherCompartments)
                        .contains(c))
                .distinct().toArray(Character[]::new);
    }

    public Character[] sharedItems(Rucksack otherRucksack){
        return sharedItems(ArrayUtils.addAll(otherRucksack.firstCompartment, otherRucksack.secondCompartment));
    }

    public static int charToPrio(Character character){
        return character - (Character.isUpperCase(character) ? 38 : 96);
    }

    @Override
    public String toString() {
        return "First: " + Arrays.toString(firstCompartment) + " Second: " + Arrays.toString(secondCompartment) + " Shared: " + Arrays.toString(sharedItems());
    }
}
