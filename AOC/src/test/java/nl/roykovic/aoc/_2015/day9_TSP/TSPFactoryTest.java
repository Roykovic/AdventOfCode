package nl.roykovic.aoc._2015.day9_TSP;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TSPFactoryTest {

    @Test
    void testExampleShortestPath() throws IOException {
        File input = new File("src/test/resources/2015/TSPExampleInput.txt");
        List<Integer> distances = new TSPFactory().generateFromFile(input);

        assertEquals(605, Collections.min(distances));
    }
    @Test
    void testActualShortestPath() throws IOException {
        File input = new ClassPathResource("2015/TSPInput.txt").getFile();
        List<Integer> distances = new TSPFactory().generateFromFile(input);

        assertEquals(207, Collections.min(distances));
    }

    @Test
    void testExampleLongestPath() throws IOException {
        File input = new File("src/test/resources/2015/TSPExampleInput.txt");
        List<Integer> distances = new TSPFactory().generateFromFile(input);

        assertEquals(982, Collections.max(distances));
    }
    @Test
    void testActualLongestPath() throws IOException {
        File input = new ClassPathResource("2015/TSPInput.txt").getFile();
        List<Integer> distances = new TSPFactory().generateFromFile(input);

        assertEquals(804, Collections.max(distances));
    }
}
