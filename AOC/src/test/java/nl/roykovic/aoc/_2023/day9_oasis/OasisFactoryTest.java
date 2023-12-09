package nl.roykovic.aoc._2023.day9_oasis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OasisFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "OasisTestInput.txt,true,0",
            "OasisInput.txt,false,0"
    })
    public void testExtrapolatedValues(String filename, boolean test, int expected) {
    }
}
