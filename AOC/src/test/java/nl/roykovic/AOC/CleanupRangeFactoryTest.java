package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.CleanupRange;
import nl.roykovic.AOC.domain.CleanupRangeFactory;
import nl.roykovic.AOC.domain.Rucksack;
import nl.roykovic.AOC.domain.RucksackFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CleanupRangeFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("CleanupInput.txt").getFile();
        Map<CleanupRange, CleanupRange> map = new CleanupRangeFactory().generateFromFile(input);

        long containingRanges = map.entrySet().stream().filter(e -> (e.getValue().contains(e.getKey()) || e.getKey().contains(e.getValue()))).count();

        System.out.println(containingRanges);
    }
}
