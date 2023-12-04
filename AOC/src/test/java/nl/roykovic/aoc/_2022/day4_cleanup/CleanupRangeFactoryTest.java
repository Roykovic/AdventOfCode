package nl.roykovic.aoc._2022.day4_cleanup;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanupRangeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "CleanupTestInput.txt,true,2",
            "CleanupInput.txt,false,550"
    })
    void testContainingPairs(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        var map = new CleanupRangeFactory().generateFromFile(input);

        long containingRanges = map.filter(e -> (e.getValue().contains(e.getKey()) || e.getKey().contains(e.getValue()))).count();

        assertEquals(expected, containingRanges);
    }

    @ParameterizedTest
    @CsvSource({
            "CleanupTestInput.txt,true,4",
            "CleanupInput.txt,false,931"
    })
    void testOverlappingPairs(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        var map = new CleanupRangeFactory().generateFromFile(input);

        long overlappingRanges = map.filter(e -> (e.getValue().overlaps(e.getKey()) || e.getKey().overlaps(e.getValue()))).count();

        assertEquals(expected, overlappingRanges);
    }
}
