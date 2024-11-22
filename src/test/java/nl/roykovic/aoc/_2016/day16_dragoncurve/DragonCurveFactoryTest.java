package nl.roykovic.aoc._2016.day16_dragoncurve;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DragonCurveFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "DragonCurveTestInput.txt,true,20,01100",
            "DragonCurveInput.txt,false,272,10011010010010010",
            "DragonCurveInput.txt,false,35651584,10011010010010010",
    })
    public void test(String filename, boolean test, int filesize, String expected) {
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new DragonCurveFactory().generate(input, filesize);

        assertEquals(expected, output);
    }
}
