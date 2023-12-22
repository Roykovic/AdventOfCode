package nl.roykovic.aoc._2023.day21_steps;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StepsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "StepsTestInput.txt,true,6,16",
            "StepsInput.txt,false,64,-1",
    })
    public void test(String filename, boolean test, int steps, int expected) {
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new StepsFactory().generate(input, steps);

        assertEquals(expected, output);
    }}
