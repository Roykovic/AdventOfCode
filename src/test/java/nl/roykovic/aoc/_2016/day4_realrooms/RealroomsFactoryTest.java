package nl.roykovic.aoc._2016.day4_realrooms;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

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
//            "RealroomsTestInput.txt,true,1514",
            "RealroomsInput.txt,false,548",
    })
    public void testDecypher(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new RealroomsFactory().generate(input).stream().filter(it -> it.decipherName().equals("northpole object storage ")).findFirst().orElseThrow();

        assertEquals(expected, output.getId());
    }

    @ParameterizedTest
    @CsvSource({
            "qzmt-zixmtkozy-ivhz-343[ignored],very encrypted name"
    })
    public void testDecypherString(String input, String expected) {
        var output = new RealroomsFactory().generate(Stream.of(input)).stream().map(Room::decipherName).toList();;

        assertEquals(expected.trim(), output.get(0).trim());
    }
}
