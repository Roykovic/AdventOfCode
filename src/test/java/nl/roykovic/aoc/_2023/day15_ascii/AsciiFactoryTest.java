package nl.roykovic.aoc._2023.day15_ascii;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AsciiFactoryTest {
@ParameterizedTest
    @CsvSource({
            "AsciiTestInput.txt,true,-1",
            "AsciiInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new AsciiFactory().generate(input);

        assertEquals(expected, output);
    }}
