package nl.roykovic.aoc._2023.day1_trebuchet;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TrebuchetFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "TrebuchetTestInput.txt,true,142",
            "TrebuchetInput.txt,false,54390"
    })
    void testCalibration(String filename, boolean test, int expected) throws IOException {
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
    void testSecondPartCalibration(String filename, boolean test, int expected) throws IOException {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        IntStream stream = new TrebuchetFactory().generateFromFile(input, true);

        assertEquals(expected, stream.sum());
    }
}
