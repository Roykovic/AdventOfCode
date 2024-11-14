package nl.roykovic.aoc._2016.day12_assembunny;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AssembunnyFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "AssembunnyTestInput.txt,true,0,42",
            "AssembunnyInput.txt,false,0,318020",
            "AssembunnyInput.txt,false,1,9227674",
    })
    public void test(String filename, boolean test, int cValue, int expected) {
        var input = FileReaderService.getLinesFromFile(2016, filename, test);
        var output = new AssembunnyFactory().generate(input, cValue);

        assertEquals(expected, output);
    }
}
