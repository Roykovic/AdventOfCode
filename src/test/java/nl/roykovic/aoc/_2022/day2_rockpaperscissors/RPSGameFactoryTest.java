package nl.roykovic.aoc._2022.day2_rockpaperscissors;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPSGameFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "RockPaperScissorTestInput.txt,true,15, false",
            "RockPaperScissorTestInput.txt,true,12, true",
            "RockPaperScissorInput.txt,false,11150, false",
            "RockPaperScissorInput.txt,false,8295, true"
    })
    void testScore(String filename, boolean test, int expected, boolean elvesSolution) throws IOException {
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        assertEquals(expected,  new RPSGameFactory().generateFromFile(input, elvesSolution));
    }
}
