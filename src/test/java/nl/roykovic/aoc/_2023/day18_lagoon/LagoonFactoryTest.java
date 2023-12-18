package nl.roykovic.aoc._2023.day18_lagoon;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LagoonFactoryTest {
@ParameterizedTest
    @CsvSource({
            "LagoonTestInput.txt,true,-1",
            "LagoonInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new LagoonFactory().generate(input);

        assertEquals(expected, output);
    }}
