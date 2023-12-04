package nl.roykovic.aoc._2023.day4_scratchcards;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScratchCardFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "ScratchCardTestInput.txt,true,13",
            "ScratchCardInput.txt,false,26346"
    })
    public void testScratchcardPoints(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        assertEquals(expected, new ScratchCardFactory().generatePoints(input));
    }
}
