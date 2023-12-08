package nl.roykovic.aoc._2023.day8_maps;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "MapsTestInputOne.txt,true,2",
            "MapsTestInputTwo.txt,true,6",
            "MapsInput.txt,false,20513"
    })
    public void testRouteToEnd(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2023, filename, test);

        assertEquals(expected, new MapsFactory().generate(input));
    }
}
