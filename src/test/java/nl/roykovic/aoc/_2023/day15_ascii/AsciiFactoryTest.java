package nl.roykovic.aoc._2023.day15_ascii;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AsciiFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "HASH,52",
            "rn=1,30",
            "cm-,253",
            "rn,0",
            "cm,0",
            "qp,1",
            "pc,3",
            "ot,3",
            "ab,3",
    })
    public void testAlgorithm(String input, int expected) {
        assertEquals(expected, new AsciiFactory().getHashAlgorithmValue(input));
    }

    @ParameterizedTest
    @CsvSource({
            "AsciiTestInput.txt,true,1320",
            "AsciiInput.txt,false,515495",
    })
    public void testInitializationSequence(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2023, filename, test);
        var output = Arrays.stream(
                input.split(","))
                .mapToLong(new AsciiFactory()::getHashAlgorithmValue)
                .sum();

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "AsciiTestInput.txt,true,145",
            "AsciiInput.txt,false,229349",
    })
    public void testFocusingPower(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2023, filename, test);
        var output = new AsciiFactory().generate(input);

        assertEquals(expected, output);
    }
}
