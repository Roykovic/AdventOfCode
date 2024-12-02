package nl.roykovic.aoc._2024.day2_unusualdata;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UnusualDataFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "UnusualDataTestInput.txt,true,2",
            "UnusualDataInput.txt,false,356",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2024, filename, test);
        var output = new UnusualDataFactory().countSafe(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "UnusualDataTestInput.txt,true,4",
            "UnusualDataInput.txt,false,413",
    })
    public void testp2(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2024, filename, test);
        var output = new UnusualDataFactory().countSafeWithDampener(input);

        assert(test || output > 409);
        assertEquals(expected, output);
    }
}
