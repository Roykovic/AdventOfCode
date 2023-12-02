package nl.roykovic.aoc._2023.day2_cubegame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CubeGameFactory {
    public List<Map<String, Integer>> generateFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        return reader.lines()
                .map(it -> it.split(":")[1])
                .map(it -> it.split(";"))
                .map(it -> Arrays.stream(it).map(str -> str.split(",")).toList())
                .map(it -> it.stream()
                        .map( arr -> Arrays.stream(arr)
                                .collect(Collectors
                                        .toMap(p -> p.trim().split(" ")[1], p -> p.trim().split(" ")[0]))
                        ))
                .map(it -> it
                        .flatMap(m ->  m.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, m -> Integer.parseInt(m.getValue()), Integer::sum)))
                .toList();
    }
}