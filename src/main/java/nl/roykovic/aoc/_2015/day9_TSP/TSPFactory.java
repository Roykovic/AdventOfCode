package nl.roykovic.aoc._2015.day9_TSP;

import nl.roykovic.aoc.utils.TSPService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;

public class TSPFactory {

    public List<Integer> generateFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = reader.lines().toList();

        //Let's get an array with all distinct cities (cities are the first and third word in every line
        HashSet<String> distinctCities = new HashSet<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            distinctCities.add(parts[0]);
            distinctCities.add(parts[2]);
        }
        String[] citiesArray = distinctCities.toArray(new String[0]);


        //now create a lookup table for every combination of cities with its distance
        int[][] distanceArray = new int[citiesArray.length][citiesArray.length];

        for (String line : lines) {
            String[] parts = line.split(" ");

            int cityFromIndex = ArrayUtils.indexOf(citiesArray, parts[0]);
            int cityToIndex = ArrayUtils.indexOf(citiesArray, parts[2]);

            //add both, because every distance is listed once. But distance a -> b == distance b -> a
            distanceArray[cityFromIndex][cityToIndex] = NumberUtils.toInt(parts[4]);
            distanceArray[cityToIndex][cityFromIndex] = NumberUtils.toInt(parts[4]);

        }

        return TSPService.calculateDistances(distanceArray, false);
    }
}
