package nl.roykovic.aoc._2022.lavadroplets;

import nl.roykovic.aoc.utils.Face;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LavaDropletsFactoryTest {
    @Test
    void testExampleSurfaceArea() throws IOException {
        File input = new File("src/test/resources/2022/LavaDropletsTestInput.txt");
        List<Face> faces = new LavaDropletsFactory().generateFromFile(input);

        assertEquals(64, faces.size());
    }

    @Test
    void testActualSurfaceArea() throws IOException {
        File input = new ClassPathResource("2022/LavaDropletsInput.txt").getFile();
        List<Face> faces = new LavaDropletsFactory().generateFromFile(input);

        assertEquals(4308, faces.size());
    }
}
