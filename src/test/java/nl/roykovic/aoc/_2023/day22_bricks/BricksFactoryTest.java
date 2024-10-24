package nl.roykovic.aoc._2023.day22_bricks;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BricksFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "BricksTestInput.txt,true,-1",
//            "BricksInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new BricksFactory().generate(input);

        assertEquals(expected, output);
    }}
