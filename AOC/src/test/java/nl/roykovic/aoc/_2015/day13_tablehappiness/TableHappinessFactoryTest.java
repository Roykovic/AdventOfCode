package nl.roykovic.aoc._2015.day13_tablehappiness;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableHappinessFactoryTest {
    @Test
    void testExampleHappiestPath() throws IOException {
        File input = new File("src/test/resources/2015/TableHappinessExampleInput.txt");
        List<Integer> distances = new TableHappinessFactory().generateFromFile(input);

        assertEquals(330, Collections.max(distances));
    }

    @Test
    void testActualHappiestPath() throws IOException {
        File input = new ClassPathResource("2015/TableHappinessInput.txt").getFile();
        List<Integer> distances = new TableHappinessFactory().generateFromFile(input);

        assertEquals(733, Collections.max(distances));
    }

    @Test
    void testActualHappiestPathWithMe() throws IOException {
        File input = new ClassPathResource("2015/TableHappinessWithMeInput.txt").getFile();
        List<Integer> distances = new TableHappinessFactory().generateFromFile(input);

        assertEquals(725, Collections.max(distances));
    }
}
