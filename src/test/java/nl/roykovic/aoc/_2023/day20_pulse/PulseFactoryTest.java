package nl.roykovic.aoc._2023.day20_pulse;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PulseFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "PulseTestInputOne.txt,true,32000000",
            "PulseTestInputTwo.txt,true,11687500",
            "PulseInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new PulseFactory().generate(input);

        assertEquals(expected, output);
    }}
