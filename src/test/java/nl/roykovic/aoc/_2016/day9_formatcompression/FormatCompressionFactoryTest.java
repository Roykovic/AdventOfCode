package nl.roykovic.aoc._2016.day9_formatcompression;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FormatCompressionFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "FormatCompressionInput.txt,false,110346",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new FormatCompressionFactory().decompress(input);

        assertEquals(expected, output.replace(" ", "").length());
    }

    @ParameterizedTest
    @CsvSource({
            "FormatCompressionInput.txt,false,10774309173",
    })
    public void test2(String filename, boolean test, long expected) {
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new FormatCompressionFactory().countResult(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "ADVENT,ADVENT,6",
            "A(1x5)BC,ABBBBBC,7",
            "(3x3)XYZ,XYZXYZXYZ,9",
            "A(2x2)BCD(2x2)EFG,ABCBCDEFEFG,11",
            "(6x1)(1x3)A,(1x3)A,6",
            "X(8x2)(3x3)ABCY,X(3x3)ABC(3x3)ABCY,18"

    })
    public void test(String input, String expected, int length) {
        var output = new FormatCompressionFactory().decompress(input);

        assertEquals(expected, output);
        assertEquals(length, output.length());
    }

    @ParameterizedTest
    @CsvSource({
            "(3x3)XYZ,9",
            "X(8x2)(3x3)ABCY,20",
            "(27x12)(20x12)(13x14)(7x10)(1x12)A,241920",
            "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN,445",

    })
    public void test(String input, int length) {
        var output = new FormatCompressionFactory().countResult(input);

        assertEquals(length, output);
    }
}
