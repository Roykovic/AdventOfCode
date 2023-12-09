package nl.roykovic.aoc._2023.day9_oasis;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OasisFactoryTest {


    @ParameterizedTest
    @CsvSource({
            "0 3 6 9 12 15,18",
            "1 3 6 10 15 21,28",
            "10 13 16 21 30 45,68"
    })
    public void testNextValue(String input, int expected) {

        OasisList ol = new OasisFactory().generate(Stream.of(input)).get(0);

        assertEquals(expected, ol.getNextValue());
    }


    @ParameterizedTest
    @CsvSource({
            "0 3 6 9 12 15,-3",
            "1 3 6 10 15 21,0",
            "10 13 16 21 30 45,5"
    })
    public void testPreviousValue(String input, int expected) {

        OasisList ol = new OasisFactory().generate(Stream.of(input)).get(0);

        assertEquals(expected, ol.getPreviousValue());
    }

    @ParameterizedTest
    @CsvSource({
            "OasisTestInput.txt,true,114",
            "OasisInput.txt,false,0"
    })
    public void testExtrapolatedValues(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new OasisFactory().generate(input).stream().mapToLong(OasisList::getNextValue).sum();

        assertEquals(expected, output);
    }
}
