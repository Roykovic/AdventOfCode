package nl.roykovic.aoc._2015.day9_TSP;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TSPFactory {

    public List<Integer> generateFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = reader.lines().toList();

        //Lets get an array with all distinct cities (cities are the first and third word in every line
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

            //add both, because every distance is listed once. But distance a -> b == distance b-> a
            distanceArray[cityFromIndex][cityToIndex] = NumberUtils.toInt(parts[4]);
            distanceArray[cityToIndex][cityFromIndex] = NumberUtils.toInt(parts[4]);

        }

        //Calculate all possible orders of the list (by index, because we made a lookup array that can get a city by its index)
        List<Integer> indexList = IntStream.range(0, citiesArray.length).boxed().toList();

        List<List<Integer>> possiblePermutations = permutations(indexList).toList();

        List<Integer> distances = new ArrayList<>();

        //now for every possible order of cities, calculate how long each route is.
        //We do this by getting all distances from the lookup table from this city in the list to the next.
        //Untill we hit the end of the route
        for (List<Integer> possibleRoute : possiblePermutations) {
            int distance = 0;
            for (int i = 0; i < possibleRoute.size(); i++) {
                if (i < (possibleRoute.size() - 1)) {
                    int currentCity = possibleRoute.get(i);
                    int nextCity = possibleRoute.get(i + 1);

                    distance += distanceArray[currentCity][nextCity];
                }
            }
            distances.add(distance);
        }

        return distances;
    }

    static Stream<List<Integer>> permutations(List<Integer> input) {
        if (input.size() == 1) {
            return Stream.of(new LinkedList<>(input));
        }
        return input.stream()
                .flatMap(first -> permutations(input.stream()
                        .filter(a -> !a.equals(first))
                        .toList())
                        .map(LinkedList::new)
                        .peek(l -> l.addFirst(first)));
    }
}
