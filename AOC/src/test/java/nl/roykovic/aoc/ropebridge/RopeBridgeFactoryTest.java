package nl.roykovic.aoc.ropebridge;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RopeBridgeFactoryTest {
    @Test
    void testExampleLocationsVisited() throws IOException {
        File input = new File("src/test/resources/RopeBridgeTestInput.txt");
        List<Map.Entry<Integer, Integer>> path = new RopeBridgeFactory().generateFromFile(input, 1);

        int distinctPathSize = path.stream().distinct().toList().size();
        assertEquals(13, distinctPathSize);
    }

    @Test
    void testActualLocationsVisited() throws IOException {
        File input = new ClassPathResource("RopeBridgeInput.txt").getFile();
        List<Map.Entry<Integer, Integer>> path = new RopeBridgeFactory().generateFromFile(input, 1);

        int distinctPathSize = path.stream().distinct().toList().size();
        assertEquals(5883, distinctPathSize);
    }

    @Test
    void testExampleLocationsVisitedLongRope() throws IOException {
        File input = new File("src/test/resources/RopeBridgeTestInput.txt");
        List<Map.Entry<Integer, Integer>> path = new RopeBridgeFactory().generateFromFile(input, 9);

        int distinctPathSize = path.stream().distinct().toList().size();
        assertEquals(1, distinctPathSize);
    }

    @Test
    void testOtherExampleLocationsVisitedLongRope() throws IOException {
        File input = new File("src/test/resources/RopeBridgeLongRopeTestInput.txt");
        List<Map.Entry<Integer, Integer>> path = new RopeBridgeFactory().generateFromFile(input, 9);

        int distinctPathSize = path.stream().distinct().toList().size();
        assertEquals(36, distinctPathSize);
    }
    @Test
    void testActualLocationsVisitedLongRope() throws IOException {
        File input = new ClassPathResource("RopeBridgeInput.txt").getFile();
        List<Map.Entry<Integer, Integer>> path = new RopeBridgeFactory().generateFromFile(input, 9);

        int distinctPathSize = path.stream().distinct().toList().size();
        assertEquals(2367, distinctPathSize);
    }
}
