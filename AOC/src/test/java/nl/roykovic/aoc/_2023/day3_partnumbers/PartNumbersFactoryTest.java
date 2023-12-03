package nl.roykovic.aoc._2023.day3_partnumbers;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartNumbersFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "PartNumbersTestInput.txt,true,4361",
            "PartNumbersInput.txt,false,520135"
    })
    public void testPartNumbers(String filename, boolean test, int expected){
        Stream<String> input = FileReaderService.getLinesFromFile(2023, filename, test);
        assertEquals(expected, new PartNumbersFactory().generatePartNumbers(input));
    }

    @ParameterizedTest
    @CsvSource({
            "PartNumbersTestInput.txt,true,467835",
            "PartNumbersInput.txt,false,72514855"
    })
    public void testGearRatios(String filename, boolean test, int expected){
        Stream<String> input = FileReaderService.getLinesFromFile(2023, filename, test);

        var answer = new PartNumbersFactory().generateGearRatios(input);

        assertEquals(expected, answer);
    }
}
