package nl.roykovic.aoc._2023.day1_trebuchet;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TrebuchetFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "TrebuchetTestInput.txt,true,142",
            "TrebuchetInput.txt,false,54390"
    })
    void testCalibration(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        IntStream stream = new TrebuchetFactory().generateFromFile(input, false);

        assertEquals(expected, stream.sum());
    }

    @ParameterizedTest
    @CsvSource({
            "TrebuchetTestInput.txt,true,142",
            "TrebuchetSecondTestInput.txt,true,281",
            "TrebuchetInput.txt,false,54277"
    })
    void testSecondPartCalibration(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        IntStream stream = new TrebuchetFactory().generateFromFile(input, true);

        assertEquals(expected, stream.sum());
    }
}
