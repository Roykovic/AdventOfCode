package nl.roykovic.aoc._2024.day2_unusualdata;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UnusualDataFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "UnusualDataTestInput.txt,true,false,2",
            "UnusualDataInput.txt,false,false,356",
            "UnusualDataTestInput.txt,true,true,4",
            "UnusualDataInput.txt,false,true,413",
    })
    public void test(String filename, boolean test, boolean dampening, int expected) {
        var input = FileReaderService.streamLinesFromFile(2024, filename, test);
        var output = new UnusualDataFactory().countSafe(input, dampening);

        assertEquals(expected, output);
    }
}
