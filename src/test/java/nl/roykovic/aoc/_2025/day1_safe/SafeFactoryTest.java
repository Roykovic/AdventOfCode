package nl.roykovic.aoc._2025.day1_safe;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class SafeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "SafeTestInput.txt,true,3,false",
            "SafeInput.txt,false,1191,false",
            "SafeTestInput.txt,true,6,true",
            "SafeTestInput2.txt,true,10,true",
            "SafeInput.txt,false,6858,true",
    })
    public void test(String filename, boolean test, int expected,boolean p2) {
        var input = FileReaderService.getLinesFromFile(2025, filename, test);
        var output = new SafeFactory().generate(input, p2);

        assert !p2 || test || (output > 6835); //That's not the right answer; your answer is too low
        assert !p2 || test || (output < 7327); //That's not the right answer; your answer is too high
        assertEquals(expected, output);
    }
}
