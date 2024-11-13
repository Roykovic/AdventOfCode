package nl.roykovic.aoc._2016.day8_screen;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ScreenFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "ScreenTestInput.txt,true,-1",
            "ScreenInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new ScreenFactory().generate(input);

        assertEquals(expected, output);
    }
}
