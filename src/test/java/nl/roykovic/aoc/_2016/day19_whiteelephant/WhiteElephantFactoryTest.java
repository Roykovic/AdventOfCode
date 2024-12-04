package nl.roykovic.aoc._2016.day19_whiteelephant;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class WhiteElephantFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "5,3",
            "10,5",
            "12,9",
            "3001330,-1",
    })
    public void test(int elves,int expected) {
        var output = new WhiteElephantFactory().generate(elves);

        assert elves != 3001330 || output > 904181;
        assert elves != 3001330 || output < 2877485;
        assertEquals(expected, output);
    }
}
