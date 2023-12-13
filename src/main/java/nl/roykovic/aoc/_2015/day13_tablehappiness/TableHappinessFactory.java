package nl.roykovic.aoc._2015.day13_tablehappiness;

import nl.roykovic.aoc.utils.TSPService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;

public class TableHappinessFactory {
    public List<Integer> generateFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = reader.lines().toList();

        //Let's get an array with all distinct cities (cities are the first and third word in every line
        HashSet<String> distinctGuests = new HashSet<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            distinctGuests.add(parts[0]);
        }
        String[] guestsArray = distinctGuests.toArray(new String[0]);


        //now create a lookup table for every combination of cities with its distance
        int[][] happinessArray = new int[guestsArray.length][guestsArray.length];

        for (String line : lines) {
            String[] parts = line.split(" ");

            String guestFrom = parts[0];
            String guestTo = parts[parts.length -1].replace(".", "");

            int guestFromIndex = ArrayUtils.indexOf(guestsArray, guestFrom);
            int guestToIndex = ArrayUtils.indexOf(guestsArray, guestTo);

            //add both, because every distance is listed once. But distance a -> b == distance b -> a
            happinessArray[guestFromIndex][guestToIndex] += calculateHappiness(parts[2], parts[3]);
            happinessArray[guestToIndex][guestFromIndex] += calculateHappiness(parts[2], parts[3]);

        }

        return TSPService.calculateDistances(happinessArray, true);
    }

    private int calculateHappiness(String keyword, String number){
        int happiness = NumberUtils.toInt(number);
        if("lose".equals(keyword)){
            happiness *= -1;
        }
        return happiness;
    }
}
