package nl.roykovic.aoc._2016.day2_passcode;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PasscodeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "PasscodeTestInput.txt,true,1985",
            "PasscodeInput.txt,false,53255",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new PasscodeFactory().generate(input);

        assertEquals(expected, output);
    }}
