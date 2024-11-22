package nl.roykovic.aoc._2016.day14_otpkey;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class OTPKeyFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "OTPKeyTestInput.txt,true,false,22728",
            "OTPKeyInput.txt,false,false,35186",
            "OTPKeyTestInput.txt,true,true,22551",
//            "OTPKeyInput.txt,false,true,22429", takes absolute ages
    })
    public void test(String filename, boolean test, boolean stretchedKeys, int expected) {
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new OTPKeyFactory().generate(input, stretchedKeys);
        assertEquals(expected, output);
    }
}
