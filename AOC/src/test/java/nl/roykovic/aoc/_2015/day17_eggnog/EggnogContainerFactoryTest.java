package nl.roykovic.aoc._2015.day17_eggnog;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EggnogContainerFactoryTest {
    @Test
    void testExampleContainerCombinations(){
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

    @Test
    void testExampleShortestContainerCombinations(){
        List<Integer> containerList = new ArrayList<>(List.of(20, 15, 10, 5, 5));
        Long combinations = new EggnogContainerFactory().calculateShortestPossibleWaysFromList(containerList, 25);

        assertEquals(3, combinations);
    }

    @Test
    void testActualShortestContainerCombinations() throws IOException {
        File input = new ClassPathResource("2015/EggnogContainerInput.txt").getFile();
        Long combinations = new EggnogContainerFactory().calculateShortestPossibleWaysFromFile(input, 150);

        assertEquals(4, combinations);
    }
}
