package nl.roykovic.aoc._2025.day5_cafetaria;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CafetariaFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "CafetariaTestInput.txt,true,3",
            "CafetariaInput.txt,false,674",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getLinesFromFile(2025, filename, test);
        var output = new CafetariaFactory().generate(input);

        assertEquals(expected, output);
    }
}
