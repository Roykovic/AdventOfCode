package nl.roykovic.aoc._2023.day13_mirror;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MirrorFactoryTest {
@ParameterizedTest
    @CsvSource({
            "MirrorTestInput.txt,true,405,0",
            "MirrorInput.txt,false,28895,0",
            "MirrorTestInput.txt,true,400,1",
            "MirrorInput.txt,false,31603,1",
    })
    public void test(String filename, boolean test, int expected, int smudges) {
        var input = FileReaderService.getFileAsString(2023, filename, test);
        var output = new MirrorFactory(smudges).generate(input);

        assertEquals(expected, output);
    }
}
