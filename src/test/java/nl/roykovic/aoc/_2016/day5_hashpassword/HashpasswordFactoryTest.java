package nl.roykovic.aoc._2016.day5_hashpassword;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class HashpasswordFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "HashpasswordTestInput.txt,true,18f47a30",
            "HashpasswordInput.txt,false,1a3099aa",
    })
    public void test(String filename, boolean test, String expected){
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new HashpasswordFactory().generate(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "HashpasswordTestInput.txt,true,05ace8e3",
            "HashpasswordInput.txt,false,694190cd",
    })
    public void testP2(String filename, boolean test, String expected){
        var input = FileReaderService.getFileAsString(2016, filename, test);
        var output = new HashpasswordFactory().generateWithPosition(input);

        assertEquals(expected, output);
    }
}
