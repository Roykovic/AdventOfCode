package nl.roykovic.aoc._2025.day4_paperrolls;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PaperRollsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "PaperRollsTestInput.txt,true,13, false",
            "PaperRollsInput.txt,false,1493, false",
            "PaperRollsTestInput.txt,true,43, true",
            "PaperRollsInput.txt,false,9194, true"
    })
    public void test(String filename, boolean test, int expected, boolean p2) {
        var input = FileReaderService.getLinesFromFile(2025, filename, test);
        var output = new PaperRollsFactory().generate(input, p2);

        assertEquals(expected, output);
    }
}
