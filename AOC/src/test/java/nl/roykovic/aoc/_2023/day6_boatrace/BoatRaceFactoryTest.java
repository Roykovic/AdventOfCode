package nl.roykovic.aoc._2023.day6_boatrace;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoatRaceFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "BoatRaceTestInput.txt,true,288",
            "BoatRaceInput.txt,false,741000"
    })
    public void testNumberOfRecordPossibilities(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        assertEquals(expected, new BoatRaceFactory().generate(input, false));
    }

    @ParameterizedTest
    @CsvSource({
            "BoatRaceTestInput.txt,true,71503",
            "BoatRaceInput.txt,false,741000"
    })
    public void testNumberOfRecordPossibilitiesOneRace(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        assertEquals(expected, new BoatRaceFactory().generate(input, true));
    }
}
