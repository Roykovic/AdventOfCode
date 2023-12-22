package nl.roykovic.aoc._2023.day20_pulse;

import nl.roykovic.aoc.utils.FileReaderService;
import nl.roykovic.aoc.utils.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PulseFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "PulseTestInputOne.txt,true,32000000",
            "PulseTestInputTwo.txt,true,11687500",
            "PulseInput.txt,false,731517480",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);

        var pf = new PulseFactory();
        var modules = pf.generate(input);

        var output = 0L;

        for(int i = 0; i < 1000; i++) {
            output = pf.pushButton(modules);
        }

        assertEquals(expected, output);
    }}
