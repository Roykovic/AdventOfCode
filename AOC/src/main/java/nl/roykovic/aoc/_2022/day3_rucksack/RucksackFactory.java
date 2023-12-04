package nl.roykovic.aoc._2022.day3_rucksack;

import org.apache.commons.lang3.ArrayUtils;

import java.util.stream.Stream;

public class RucksackFactory {

    public Stream<Rucksack> generateFromFile(Stream<String> input){
        return input.map(line -> {
            Character[] firstCompartment = ArrayUtils.toObject(line.substring(0, line.length()/2).toCharArray());
            Character[] secondCompartment = ArrayUtils.toObject(line.substring(line.length()/2).toCharArray());

           return new Rucksack(firstCompartment, secondCompartment);
        });
    }
}
