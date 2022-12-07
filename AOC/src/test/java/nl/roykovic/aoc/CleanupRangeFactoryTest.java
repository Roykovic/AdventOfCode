package nl.roykovic.aoc;

import nl.roykovic.aoc.domain.CleanupRange;
import nl.roykovic.aoc.domain.CleanupRangeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CleanupRangeFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("CleanupInput.txt").getFile();
        Map<CleanupRange, CleanupRange> map = new CleanupRangeFactory().generateFromFile(input);

        long containingRanges = map.entrySet().stream().filter(e -> (e.getValue().contains(e.getKey()) || e.getKey().contains(e.getValue()))).count();

        System.out.println(containingRanges);
    }

    @Test
    void testFactory2() throws IOException {
        File input = new ClassPathResource("CleanupInput.txt").getFile();
        Map<CleanupRange, CleanupRange> map = new CleanupRangeFactory().generateFromFile(input);

        long overlappingRanges = map.entrySet().stream().filter(e -> (e.getValue().overlaps(e.getKey()) || e.getKey().overlaps(e.getValue()))).count();

        System.out.println(overlappingRanges);
    }
}
