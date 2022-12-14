package nl.roykovic.aoc._2015.apartment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ApartmentFactory {
    public IntStream generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        return this.generateFromString(reader.lines().findFirst().orElseThrow(IllegalArgumentException::new));

    }

    public IntStream generateFromString(String input){

        return input.chars().mapToObj(it -> (char) it).mapToInt(it -> {
            if (it == '(') {
                return 1;
            }
            return -1;
        });
    }
}
