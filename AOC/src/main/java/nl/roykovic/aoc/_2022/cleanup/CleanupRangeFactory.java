package nl.roykovic.aoc._2022.cleanup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class CleanupRangeFactory {
    public Map<CleanupRange, CleanupRange> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        Map<CleanupRange, CleanupRange> cleanupRangeMap = new HashMap<>();

        for(String line: lines){
            String[] ranges = line.split(",");

            int[] firstRange = Arrays.stream(ranges[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] secondRange = Arrays.stream(ranges[1].split("-")).mapToInt(Integer::parseInt).toArray();

            CleanupRange firstCleanupRange = new CleanupRange(firstRange[0], firstRange[1]);
            CleanupRange secondCleanupRange = new CleanupRange(secondRange[0], secondRange[1]);

            cleanupRangeMap.put(firstCleanupRange, secondCleanupRange);
        }
        return cleanupRangeMap;
    }
}
