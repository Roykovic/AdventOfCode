package nl.roykovic.aoc._2015.day17_eggnog;

import nl.roykovic.aoc._2015.day13_tablehappiness.TableHappinessFactory;
import nl.roykovic.aoc._2015.day14_reindeerrace.Reindeer;
import nl.roykovic.aoc._2015.day14_reindeerrace.ReindeerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EggnogContainerFactoryTest {
    @Test
    void testExampleContainerCombinations() throws IOException {
        List<Integer> containerList = new ArrayList<>(List.of(20, 15, 10, 5, 5));
        Long combinations = new EggnogContainerFactory().calculatePossibleWaysFromList(containerList, 25);

        assertEquals(4, combinations);
    }

    @Test
    void testActualContainerCombinations() throws IOException {
        File input = new ClassPathResource("2015/EggnogContainerInput.txt").getFile();
        Long combinations = new EggnogContainerFactory().calculatePossibleWaysFromFile(input, 150);

        assertEquals(4372, combinations);
    }
}
