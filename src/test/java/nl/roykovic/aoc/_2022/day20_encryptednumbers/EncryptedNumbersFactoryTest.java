package nl.roykovic.aoc._2022.day20_encryptednumbers;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptedNumbersFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "EncryptedNumbersTestInput.txt,true,1,1,3",
            "EncryptedNumbersInput.txt,false,1,1,17490",
            "EncryptedNumbersTestInput.txt,true,10,811589153,1623178306",
            "EncryptedNumbersInput.txt,false,10,811589153,1632917375836"
    })
    void testExampleEncryptedNumber(String filename, boolean test, int times, int keys,long expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        EncryptedNumbersList list = new EncryptedNumbersFactory().generateFromFile(input, keys);
        list.decrypt(times);

        assertEquals(expected, list.findNthNumberAfterZero(1000).getValue() + list.findNthNumberAfterZero(2000).getValue() +list.findNthNumberAfterZero(3000).getValue());
    }
}
