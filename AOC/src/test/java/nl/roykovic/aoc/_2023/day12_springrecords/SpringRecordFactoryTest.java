package nl.roykovic.aoc._2023.day12_springrecords;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringRecordFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "SpringRecordTestInput.txt,true,-1",
            "SpringRecordInput.txt,false,-1",
    })
    public void testMissingRecords(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new SpringRecordFactory().generate(input);

        assertEquals(expected, output);
    }
}
