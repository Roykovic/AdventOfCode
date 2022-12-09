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
        List<Map.Entry<Integer, Integer>> path = new RopeBridgeFactory().generateFromFile(input);

        int distinctPathSize = path.stream().distinct().toList().size();
        assertEquals(13, distinctPathSize);
    }

    @Test
    void testActualLocationsVisited() throws IOException {
        File input = new ClassPathResource("RopeBridgeInput.txt").getFile();
        List<Map.Entry<Integer, Integer>> path = new RopeBridgeFactory().generateFromFile(input);

        List<Map.Entry<Integer, Integer>> distinctPath = path.stream().distinct().toList();

        System.out.println(distinctPath);

        int distinctPathSize = distinctPath.size();
        assertEquals(5883, distinctPathSize);
    }
}
