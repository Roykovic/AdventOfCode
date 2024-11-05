package nl.roykovic.aoc._2015.day24_balance;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BalanceFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "BalanceTestInput.txt,true,99",
            "BalanceInput.txt,false,10439961859",
    })
    public void test(String filename, boolean test, Long expected) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);
        var output = new BalanceFactory().generate(input);
        assert(output < 341786451067L);
        assertEquals(expected, output);
    }}
