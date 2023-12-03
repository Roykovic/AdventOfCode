package nl.roykovic.aoc._2023.day3_partnumbers;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartNumbersFactoryTest {

    @Test
    public void testExamplePartNumbers(){
        Stream<String> input = FileReaderService.getLinesFromFile(2023, "PartNumbersTestInput.txt", true);

        var answer = new PartNumbersFactory().generate(input);

        assertEquals(4361, answer);
    }

    @Test
    public void testActualPartNumbers(){
        Stream<String> input = FileReaderService.getLinesFromFile(2023, "PartNumbersInput.txt", false);

        var answer = new PartNumbersFactory().generate(input);

        assertEquals(0, answer);
    }
}
