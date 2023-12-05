package nl.roykovic.aoc._2023.day5_garden;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GardenFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "GardenTestinput.txt,true,0",
            "GardenInput.txt,false,0"
    })
    public void testGardenClosesLocation(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        assertEquals(expected, new GardenFactory().generate(input));
    }
}
