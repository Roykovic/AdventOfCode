package nl.roykovic.aoc._2023.day13_mirror;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MirrorFactoryTest {
@ParameterizedTest
    @CsvSource({
//            "MirrorTestInput.txt,true,405",
            "MirrorInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2023, filename, test);
        var output = new MirrorFactory().generate(input);

        assertEquals(expected, output);
    }}
