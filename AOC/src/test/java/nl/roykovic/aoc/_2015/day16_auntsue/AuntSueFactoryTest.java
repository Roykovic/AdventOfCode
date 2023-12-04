package nl.roykovic.aoc._2015.day16_auntsue;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuntSueFactoryTest {
    @Test
    void testActualAuntSue() throws IOException {
        File input = new ClassPathResource("2015/AuntSueInput.txt").getFile();

        Map<String, String> knownAttributes = Map.of(
                "children", "3",
                "cats", "7",
                "samoyeds", "2",
                "pomeranians", "3",
                "akitas", "0",
                "vizslas", "0",
                "goldfish", "5",
                "trees", "3",
                "cars", "2",
                "perfumes", "1");

        String correctSue = new AuntSueFactory().generateFromFile(input, knownAttributes);

        assertEquals("Sue 213", correctSue);
    }

    @Test
    void testActualAuntSueWithRanges() throws IOException {
        File input = new ClassPathResource("2015/AuntSueInput.txt").getFile();

        Map<String, String> knownAttributes = Map.of(
                "children", "3",
                "cats", ">7",
                "samoyeds", "2",
                "pomeranians", "<3",
                "akitas", "0",
                "vizslas", "0",
                "goldfish", "<5",
                "trees", ">3",
                "cars", "2",
                "perfumes", "1");

        String correctSue = new AuntSueFactory().generateFromFile(input, knownAttributes);

        assertEquals("Sue 323", correctSue);
    }
}
