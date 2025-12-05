package nl.roykovic.aoc._2025.day2_invalidids;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class InvalidIdsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "InvalidIdsTestInput.txt,true,-1",
            "InvalidIdsInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2025, filename, test);
        var output = new InvalidIdsFactory().generate(input);

        assertEquals(expected, output);
    }
}
