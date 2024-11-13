package nl.roykovic.aoc._2016.day6_repetitioncode;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class RepetitioncodeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "RepetitioncodeTestInput.txt,true,easter",
            "RepetitioncodeInput.txt,false,ikerpcty",
    })
    public void test(String filename, boolean test, String expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new RepetitioncodeFactory().generate(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "RepetitioncodeTestInput.txt,true,advent",
            "RepetitioncodeInput.txt,false,ikerpcty",
    })
    public void testLeastFavorite(String filename, boolean test, String expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new RepetitioncodeFactory().generateLeastFavorite(input);

        assertEquals(expected, output);
    }
}
