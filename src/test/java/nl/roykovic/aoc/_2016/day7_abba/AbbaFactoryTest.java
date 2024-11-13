package nl.roykovic.aoc._2016.day7_abba;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbbaFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "AbbaTestInput.txt,true,2",
            "AbbaInput.txt,false,105",
    })
    public void testTLS(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new AbbaFactory().countTLS(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "AbbaInput.txt,false,258",
    })
    public void testSSL(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var output = new AbbaFactory().countSSL(input);

        assert(output < 329);
        assertEquals(expected, output);
    }



    @ParameterizedTest
    @CsvSource({
            "aba[bab]xyz,true",
            "xyx[xyx]xyx,false",
            "aaa[kek]eke,true",
            "zazbz[bzb]cdb,true",
    })
    public void testSSL(String address, boolean expected) {
        var output = new AbbaFactory().supportsSSL(address);

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
