package nl.roykovic.aoc._2016.day13_maze;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MazeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "MazeTestInput.txt,true,-1",
            "MazeInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new MazeFactory().generate(input);

        assertEquals(expected, output);
    }
}
