package nl.roykovic.aoc._2023.day18_lagoon;

import nl.roykovic.aoc.utils.FileReaderService;
import nl.roykovic.aoc.utils.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LagoonFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "LagoonTestInput.txt,true,62",
            "LagoonInput.txt,false,62573",
    })
    public void testLagoonCapacity(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new LagoonFactory().generate(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "LagoonTestInput.txt,true,952408144115",
            "LagoonInput.txt,false,54662804037719",
    })
    public void testLagoonCapacityHexNumbers(String filename, boolean test, BigInteger expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        input = LagoonFactory.sanitizeInput(input);
        var output = new LagoonFactory().generate(input);
        assertEquals(expected, output);
    }}
