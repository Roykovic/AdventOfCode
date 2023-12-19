package nl.roykovic.aoc._2023.day19_sorter;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class SorterFactoryTest {
@ParameterizedTest
    @CsvSource({
            "SorterTestInput.txt,true,19114",
            "SorterInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new SorterFactory().generate(input);

        assertEquals(expected, output);
    }}
