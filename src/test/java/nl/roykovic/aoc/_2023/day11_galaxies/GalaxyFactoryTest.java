package nl.roykovic.aoc._2023.day11_galaxies;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "GalaxyTestInput.txt,true,374,2",
            "GalaxyInput.txt,false,9556712,2",
            "GalaxyTestInput.txt,true,1030,10",
            "GalaxyTestInput.txt,true,8410,100",
            "GalaxyInput.txt,false,678626199476,1000000",
    })
    public void testShortstPath(String filename, boolean test, long expected, int growth) {
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new GalaxyFactory().generate(input, growth);

        assertEquals(expected, output);
    }

}
