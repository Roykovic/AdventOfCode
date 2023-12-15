package nl.roykovic.aoc._2023.day12_springrecords;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringRecordFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "???.###,1,113",
            ".??..??...?##.,4,113",
            "?#?#?#?#?#?#?#?.,1,1316",
            "????.#...#...,1,411",
            "????.######..#####.,4,165",
            "?###????????,10,321",
            ".##.?#??.#.?#,1,2111",
    })
    public void testCalculateWays(String input, int expected, String configuration) {
        var configurationArr = Arrays.stream(configuration.split("")).mapToInt(Integer::parseInt).boxed().toList();
        var output = new SpringRecordFactory().calculateWays(input, configurationArr);
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "SpringRecordTestInput.txt,true,21,false",
            "SpringRecordInput.txt,false,7110,false",
            "SpringRecordTestInput.txt,true,525152,true",
            "SpringRecordInput.txt,false,1566786613613,true",
    })
    public void testMissingRecords(String filename, boolean test, long expected,boolean unfold) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new SpringRecordFactory().generate(input, unfold);

        assertEquals(expected, output);
    }
}
