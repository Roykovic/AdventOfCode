package nl.roykovic.aoc._2022.day1_elf;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElfFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "ElfTestInput.txt,true,24000",
            "ElfCalorieInput.txt,false,70116"
    })
    void testCalories(String filename, boolean test, int expected) throws IOException {
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        assertEquals(expected, new ElfFactory().generateFromFile(input).get(0).getCalories());
    }

    @ParameterizedTest
    @CsvSource({
            "ElfTestInput.txt,true,45000",
            "ElfCalorieInput.txt,false,206582"
    })
    void testTopThreeCalories(String filename, boolean test, int expected) throws IOException {
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        assertEquals(expected, new ElfFactory().generateFromFile(input)
                                                    .subList(0, 3).stream()
                                                    .mapToLong(Elf::getCalories)
                                                    .sum());
    }
}
