package nl.roykovic.aoc._2025.day3_batterybanks;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BatteryBanksFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "BatteryBanksTestInput.txt,true,357,2",
            "BatteryBanksInput.txt,false,17452,2",
            "BatteryBanksTestInput.txt,true,3121910778619,12",
            "BatteryBanksInput.txt,false,173300819005913,12",
    })
    public void test(String filename, boolean test, long expected, int length) {
        var input = FileReaderService.streamLinesFromFile(2025, filename, test);
        var output = new BatteryBanksFactory().generate(input, length);

        assertEquals(expected, output);
    }
}
