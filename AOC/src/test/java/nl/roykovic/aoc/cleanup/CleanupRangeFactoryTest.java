package nl.roykovic.aoc.cleanup;

import nl.roykovic.aoc.cleanup.CleanupRange;
import nl.roykovic.aoc.cleanup.CleanupRangeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanupRangeFactoryTest {
    @Test
    void testContainingExamplePairs() throws IOException {
        File input = new File("src/test/resources/CleanupTestInput.txt");
        Map<CleanupRange, CleanupRange> map = new CleanupRangeFactory().generateFromFile(input);

        long containingRanges = map.entrySet().stream().filter(e -> (e.getValue().contains(e.getKey()) || e.getKey().contains(e.getValue()))).count();

        assertEquals(2, containingRanges);
    }

    @Test
    void testContainingActualPairs() throws IOException {
        File input = new ClassPathResource("CleanupInput.txt").getFile();
        Map<CleanupRange, CleanupRange> map = new CleanupRangeFactory().generateFromFile(input);

        long containingRanges = map.entrySet().stream().filter(e -> (e.getValue().contains(e.getKey()) || e.getKey().contains(e.getValue()))).count();

        assertEquals(550, containingRanges);
    }

    @Test
    void testOverlappingExamplePairs() throws IOException {
        File input = new File("src/test/resources/CleanupTestInput.txt");
        Map<CleanupRange, CleanupRange> map = new CleanupRangeFactory().generateFromFile(input);

        long overlappingRanges = map.entrySet().stream().filter(e -> (e.getValue().overlaps(e.getKey()) || e.getKey().overlaps(e.getValue()))).count();

        assertEquals(4, overlappingRanges);
    }

    @Test
    void testOverlappingActualPairs() throws IOException {
        File input = new ClassPathResource("CleanupInput.txt").getFile();
        Map<CleanupRange, CleanupRange> map = new CleanupRangeFactory().generateFromFile(input);

        long overlappingRanges = map.entrySet().stream().filter(e -> (e.getValue().overlaps(e.getKey()) || e.getKey().overlaps(e.getValue()))).count();

        assertEquals(931, overlappingRanges);
    }
}
