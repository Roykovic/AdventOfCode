package nl.roykovic.aoc._2023.day2_cubegame;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CubeGameFactory {
    public List<List<Map<String, Integer>>> generate(Stream<String> input){
        return input
                .map(it -> it.split(":")[1])
                .map(it -> it.split(";"))
                .map(it -> Arrays.stream(it).map(str -> str.split(",")))
                .map(it -> it
                        .map( arr -> Arrays.stream(arr)
                                .collect(Collectors
                                        .toMap(p -> p.trim().split(" ")[1], p -> Integer.parseInt(p.trim().split(" ")[0])))
                        ).toList()).toList();
    }
}