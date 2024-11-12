package nl.roykovic.aoc._2016.day2_passcode;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PasscodeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "PasscodeTestInput.txt,true,1985",
            "PasscodeInput.txt,false,53255",
    })
    public void test(String filename, boolean test, String expected) {

        List<Coord> passCodeCoords = List.of(
                new Coord(100000, 1000), //stuffing to make it 1-indexed so the indexes are the same as the number on the pad
                new Coord(0,0),
                new Coord(1,0),
                new Coord(2,0),
                new Coord(0,1),
                new Coord(1,1),
                new Coord(2,1),
                new Coord(0,2),
                new Coord(1,2),
                new Coord(2,2)
        );

        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new PasscodeFactory().generate(input, passCodeCoords);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "PasscodeTestInput.txt,true,5DB3",
            "PasscodeInput.txt,false,7423A",
    })
    public void testP2(String filename, boolean test, String expected) {

        List<Coord> passCodeCoords = List.of(
                new Coord(100000, 1000), //stuffing to make it 1-indexed so the indexes are the same as the number on the pad
                new Coord(2,0),
                new Coord(1,1),
                new Coord(2,1),
                new Coord(3,1),
                new Coord(0,2),
                new Coord(1,2),
                new Coord(2,2),
                new Coord(3,2),
                new Coord(4,2),
                new Coord(1,3),
                new Coord(2,3),
                new Coord(3,3),
                new Coord(2,4)
        );

        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new PasscodeFactory().generate(input, passCodeCoords);

        assertEquals(expected, output);
    }
}
