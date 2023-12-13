package nl.roykovic.aoc._2023.day8_maps;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapsFactory {
    public Map<String, MapNode> generate(Stream<String> input){
        return input.map(it ->
                        Arrays.stream(
                                it.split(" = "))
                                .map(str -> {
                                    str = str.replaceAll("[()]","");
                                    return str.split(", ");
                }).toList())
                .collect(Collectors.toMap(it -> it.get(0)[0], it-> new MapNode(it.get(1)[0], it.get(1)[1])));
    }
}
