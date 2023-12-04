package nl.roykovic.aoc._2022.day4_cleanup;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class CleanupRangeFactory {
    public Stream<Map.Entry<CleanupRange, CleanupRange>> generateFromFile(Stream<String> input){
        return input
                .map(line -> {
                    String[] ranges = line.split(",");

                    int[] firstRange = Arrays.stream(ranges[0].split("-")).mapToInt(Integer::parseInt).toArray();
                    int[] secondRange = Arrays.stream(ranges[1].split("-")).mapToInt(Integer::parseInt).toArray();

                    CleanupRange firstCleanupRange = new CleanupRange(firstRange[0], firstRange[1]);
                    CleanupRange secondCleanupRange = new CleanupRange(secondRange[0], secondRange[1]);

                    return Map.entry(firstCleanupRange, secondCleanupRange);
                });
    }
}
