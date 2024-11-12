package nl.roykovic.aoc._2016.day3_triangles;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TrianglesFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "TrianglesTestInput.txt,true,0",
            "TrianglesInput.txt,false,862",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new TrianglesFactory().generate(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "TrianglesInput.txt,false,1577",
    })
    public void testByColumn(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new TrianglesFactory().generateByColumn(input);

        assert(output < 3407); //answer too high
        assertEquals(expected, output);
    }
}
