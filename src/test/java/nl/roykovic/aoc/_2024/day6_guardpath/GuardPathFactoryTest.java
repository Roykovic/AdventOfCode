package nl.roykovic.aoc._2024.day6_guardpath;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class GuardPathFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "GuardPathTestInput.txt,true,41",
            "GuardPathInput.txt,false,4776",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getLinesFromFile(2024, filename, test);
        var output = new GuardPathFactory().generate(input);

        assertEquals(expected, output);
    }
}
