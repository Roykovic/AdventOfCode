package nl.roykovic.aoc._2016.day1_coordgrid;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CoordgridFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "CoordgridTestInput.txt,true,5",
            "CoordgridInput.txt,false,279",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new CoordgridFactory().generate(input);

        assertEquals(expected, output);
    }}
