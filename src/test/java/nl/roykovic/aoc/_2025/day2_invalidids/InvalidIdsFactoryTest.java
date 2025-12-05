package nl.roykovic.aoc._2025.day2_invalidids;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class InvalidIdsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "InvalidIdsTestInput.txt,true,1227775554, false",
            "InvalidIdsInput.txt,false,44854383294, false",
            "InvalidIdsTestInput.txt,true,4174379265, true",
            "InvalidIdsInput.txt,false,55647141923, true",
    })
    public void test(String filename, boolean test, long expected, boolean p2) {
        var input = FileReaderService.getFileAsString(2025, filename, test);
        var output = new InvalidIdsFactory().generate(input, p2);

        assertEquals(expected, output);
    }
}
