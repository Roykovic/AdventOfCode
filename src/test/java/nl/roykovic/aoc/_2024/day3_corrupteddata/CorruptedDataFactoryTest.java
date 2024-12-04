package nl.roykovic.aoc._2024.day3_corrupteddata;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CorruptedDataFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "CorruptedDataTestInput.txt,true,false,161",
            "CorruptedDataInput.txt,false,true,189600467",
            "CorruptedDataTestInputTwo.txt,true,false,48",
            "CorruptedDataInput.txt,false,false,107069718",
    })
    public void test(String filename, boolean test, boolean ignoreDoAndDont, int expected) {
        var input = FileReaderService.getFileAsString(2024, filename, test);
        var output = new CorruptedDataFactory().generate(input,ignoreDoAndDont);


        assert test || output < 295953097: "Your answer is too high";
        assert test || output < 220088153: "Your answer is too high";
        assertEquals(expected, output);
    }


    @ParameterizedTest
    @CsvSource({
            "CorruptedDataTestInput.txt,true",
    })
    public void regexTest(String filename, boolean test) {
        var input = FileReaderService.getFileAsString(2024, filename, test);
        var output = new CorruptedDataFactory().getMatchesFromString(input, true);

        assert output.size() == 4;
        assert output.contains("mul(2,4)");
        assert output.contains("mul(5,5)");
        assert output.contains("mul(11,8)");
        assert output.contains("mul(8,5)");
    }

    @ParameterizedTest
    @CsvSource({
            "CorruptedDataTestInputTwo.txt,true",
    })
    public void regexTestWithDoAndDont(String filename, boolean test) {
        var input = FileReaderService.getFileAsString(2024, filename, test);
        var output = new CorruptedDataFactory().getMatchesFromString(input, false);

        assert output.size() == 2;
        assert output.contains("mul(2,4)");
        assert output.contains("mul(8,5)");
    }
}
