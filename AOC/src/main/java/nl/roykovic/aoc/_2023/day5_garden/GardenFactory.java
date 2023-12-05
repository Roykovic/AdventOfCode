package nl.roykovic.aoc._2023.day5_garden;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class GardenFactory {
    public Map generate(String input){

        String[] result = input.split("\\r\\n[\\r\\n]+");
        String seedString = result[0];

        return Arrays.stream(result).skip(1)
                .map(it -> it.split(":"))
                .map(it -> Map.entry(it[0], createMapping(it[1])))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private RangeMap createMapping(String numbers){
        var result = Arrays.stream(numbers.trim().split("\\r\\n"))
                .map(it -> it.trim().split(" "))
                .map(nums -> {
                    long source = Long.parseLong(nums[1]);
                    long destination = Long.parseLong(nums[0]);
                    long size =  Long.parseLong(nums[2]);
                    return Map.of(new Range(source, source+size), new Range(destination, destination+size));
                })
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new RangeMap(result);
    }
}
