package nl.roykovic.aoc._2015.day15_ingredients;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class IngredientsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "IngredientsTestInput.txt,true,62842880",
            "IngredientsInput.txt,false,18965440",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);

        IngredientsFactory factory = new IngredientsFactory();

        var output = factory.generate(input);
        int max = factory.calculateBestCookieScore(100, Integer.MAX_VALUE, output);

        assertEquals(expected, max);
    }

    @ParameterizedTest
    @CsvSource({
            "IngredientsTestInput.txt,true,57600000",
            "IngredientsInput.txt,false,15862900",
    })
    public void testWithCalories(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);

        IngredientsFactory factory = new IngredientsFactory();

        var output = factory.generate(input);
        int max = factory.calculateBestCookieScore(100, 500, output);

        assertEquals(expected, max);
    }
}
