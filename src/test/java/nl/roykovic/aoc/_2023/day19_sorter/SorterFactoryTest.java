package nl.roykovic.aoc._2023.day19_sorter;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class SorterFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "SorterTestInput.txt,true,19114",
            "SorterInput.txt,false,398527",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        SorterFactory sf = new SorterFactory();
        sf.generate(input);
        var output = sf.run();

        assertEquals(expected, output);
    }
    @ParameterizedTest
    @CsvSource({
            "SorterTestInput.txt,true,167409079868000",
            "SorterInput.txt,false,133973513090020",
    })
    public void testAllPossibilities(String filename, boolean test, BigInteger expected) {
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        SorterFactory sf = new SorterFactory();
        sf.generate(input);
        var output = sf.getAllPossibilities();

        assertEquals(expected, output);
    }}
