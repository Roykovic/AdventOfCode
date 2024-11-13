package nl.roykovic.aoc._2016.day7_abba;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AbbaFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "AbbaTestInput.txt,true,2",
            "AbbaInput.txt,false,105",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new AbbaFactory().generate(input);

        assertEquals(expected, output);
    }


    @ParameterizedTest
    @CsvSource({
            "abba[mnop]qrst,true",
            "abcd[bddb]xyyx,false",
            "ioxxoj[asdfgh]zxcvbn,true",
            "aaaa[qwer]tyui,false",
    })
    public void testTLS(String address, boolean expected) {
        var output = new AbbaFactory().supportsTLS(address);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "abba,true",
            "[bddb],true",
            "[mnop],false",
            "tyui,false",
    })
    public void testABBA(String address, boolean expected) {
        var output = new AbbaFactory().hasABBA(address);

        assertEquals(expected, output);
    }
}
