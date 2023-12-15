package nl.roykovic.aoc._2022.day18_lavadroplets;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Face;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LavaDropletsFactory {
    public List<Face> generateFromFile(List<String> lines){
        return lines.stream()
                .map(Coord::new)
                .map(LavaDroplet::new)
                .map(LavaDroplet::getFaces)
                .flatMap(Arrays::stream)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();
    }
}
