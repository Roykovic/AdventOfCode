package nl.roykovic.aoc._2016.day13_maze;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MazeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "MazeTestInput.txt,true,11,7,4",
            "MazeInput.txt,false,-1,86,39",
    })
    public void test(String filename, boolean test, int expected, int endX, int endY) {
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new MazeFactory().generate(input, endX, endY);

        assertEquals(expected, output -1);
    }
}
