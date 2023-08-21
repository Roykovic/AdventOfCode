package nl.roykovic.aoc._2015.day14_reindeerrace;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReindeerFactory {
    public List<Reindeer> generateFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = reader.lines().toList();
        List<Reindeer> reindeer = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            reindeer.add(new Reindeer(parts[0], NumberUtils.toInt(parts[3]), NumberUtils.toInt(parts[6]),  NumberUtils.toInt(parts[13])));
        }

        return reindeer;
    }
}
