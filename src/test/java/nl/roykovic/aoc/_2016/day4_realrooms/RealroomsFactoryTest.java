package nl.roykovic.aoc._2016.day4_realrooms;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class RealroomsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "RealroomsTestInput.txt,true,1514",
            "RealroomsInput.txt,false,173787",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new RealroomsFactory().generate(input).stream().filter(Room::isValid).mapToInt(Room::getId).sum();;

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "RealroomsTestInput.txt,true,1514",
            "RealroomsInput.txt,false,173787",
    })
    public void testDecypher(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new RealroomsFactory().generate(input).stream().map(Room::decipherName).toList();;

        assertEquals(expected, output);
    }
}
