package nl.roykovic.aoc._2024.day1_locations;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LocationsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "LocationsTestInput.txt,true,11",
            "LocationsInput.txt,false,2164381",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2024, filename, test);
        var output = new LocationsFactory().generate(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "LocationsTestInput.txt,true,31",
            "LocationsInput.txt,false,20719933",
    })
    public void testp2(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2024, filename, test);
        var output = new LocationsFactory().countMatches(input);

        assertEquals(expected, output);
    }
}
