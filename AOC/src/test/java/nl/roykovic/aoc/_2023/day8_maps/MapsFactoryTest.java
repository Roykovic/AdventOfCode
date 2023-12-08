package nl.roykovic.aoc._2023.day8_maps;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "MapsTestInputOne.txt,true,6440",
            "MapsTestInputTwo.txt,true,252656917",
            "MapsInput.txt,false,252656917"
    })
    public void testWinnings(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new MapsFactory().generate(input);

        assertEquals(expected, expected);
    }
}
