package nl.roykovic.aoc._2016.day8_screen;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ScreenFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "ScreenTestInput.txt,true,6",
            "ScreenInput.txt,false,110",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);

        ScreenFactory sf = new ScreenFactory();

        var output = sf.generate(input);

        sf.print();
        assertEquals(expected, output);
    }
}
