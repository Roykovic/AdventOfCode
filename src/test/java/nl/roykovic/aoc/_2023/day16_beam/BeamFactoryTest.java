package nl.roykovic.aoc._2023.day16_beam;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BeamFactoryTest {
@ParameterizedTest
    @CsvSource({
            "BeamTestInput.txt,true,46",
            "BeamInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2023, filename, test);
        var output = new BeamFactory().generate(input);

        assertEquals(expected, output);
    }}
