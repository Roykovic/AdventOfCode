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
    public List<Face> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        List<Face> faces = new ArrayList<>();

        for(String line: lines){

            LavaDroplet droplet = new LavaDroplet(new Coord(line));

            faces.addAll(Arrays.asList(droplet.getFaces()));
        }

    return faces.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(x -> x.getValue() == 1).map(Map.Entry::getKey).toList();
    }
}
