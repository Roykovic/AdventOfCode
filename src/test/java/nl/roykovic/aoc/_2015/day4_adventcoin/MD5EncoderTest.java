package nl.roykovic.aoc._2015.day4_adventcoin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MD5EncoderTest {
    @ParameterizedTest
    @CsvSource({
            "abcdef609043",
            "pqrstuv1048970",
            "bgvyzdsv254575"
    })
    void testKnownHashes(String input) throws NoSuchAlgorithmException {
        String hash = MD5Encoder.encode(input);

        assert(hash.startsWith("00000"));
    }

    @Test
    void testActualHashes() throws NoSuchAlgorithmException {

        String input = "bgvyzdsv";
        long index = 1L;
        while(true){
            String hash = MD5Encoder.encode(input + index);
            if(hash.startsWith("00000")){
                break;
            }
            index++;
        }

        assertEquals(254575, index);
    }

    @Test
    void testActualHashesSixZeroes() throws NoSuchAlgorithmException {

        String input = "bgvyzdsv";
        long index = 1L;
        while(true){
            String hash = MD5Encoder.encode(input + index);
            if(hash.startsWith("000000")){
                break;
            }
            index++;
        }

        assertEquals(1038736, index);
    }
}
