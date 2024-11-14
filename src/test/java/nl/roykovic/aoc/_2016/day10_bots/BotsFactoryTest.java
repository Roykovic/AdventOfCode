package nl.roykovic.aoc._2016.day10_bots;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BotsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "BotsTestInput.txt,true,2,5,2",
            "BotsInput.txt,false, 17, 61,118",
    })
    public void test(String filename, boolean test, int lowToLookFor, int highToLookFor, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new BotsFactory().generate(input, lowToLookFor, highToLookFor, true);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "BotsInput.txt,false, 17, 61,143153",
    })
    public void testP2(String filename, boolean test, int lowToLookFor, int highToLookFor, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new BotsFactory().generate(input, lowToLookFor, highToLookFor, false);

        assertEquals(expected, output);
    }
}
