package nl.roykovic.aoc._2023.day11_galaxies;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "GalaxyTestInput.txt,true,374",
            "GalaxyInput.txt,false,9556712",
    })
    public void testShortstPath(String filename, boolean test, int expected) {
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new GalaxyFactory().generate(input);

        assertEquals(expected, output);
    }

}
