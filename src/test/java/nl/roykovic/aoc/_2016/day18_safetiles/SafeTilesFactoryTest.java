package nl.roykovic.aoc._2016.day18_safetiles;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class SafeTilesFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "SafeTilesTestInput.txt,true,10,38",
            "SafeTilesInput.txt,false,40,1961",
            "SafeTilesInput.txt,false,400000,20000795",
    })
    public void test(String filename, boolean test, int rows, int expected) {
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new SafeTilesFactory().generate(input, rows);

        assertEquals(expected, output);
    }
}
