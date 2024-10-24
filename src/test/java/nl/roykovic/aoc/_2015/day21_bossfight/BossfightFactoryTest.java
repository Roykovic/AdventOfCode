package nl.roykovic.aoc._2015.day21_bossfight;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BossfightFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "BossfightTestInput.txt,true,-1",
            "BossfightInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);
        var output = new BossfightFactory().generate(input);

        assertEquals(expected, output);
    }}
