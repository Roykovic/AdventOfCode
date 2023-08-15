package nl.roykovic.aoc._2022.encryptednumbers;

import nl.roykovic.aoc._2022.lavadroplets.LavaDropletsFactory;
import nl.roykovic.aoc.utils.Face;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EncryptedNumbersFactoryTest {
    @Test
    void testExampleEncryptedNumber() throws IOException {
        File input = new File("src/test/resources/2022/EncryptedNumbersTestInput.txt");
        EncryptedNumbersList list = new EncryptedNumbersFactory().generateFromFile(input);
        list.decrypt();


        assertEquals(4, list.findNthNumberAfterZero(1000).getValue());
        assertEquals(-3, list.findNthNumberAfterZero(2000).getValue());
        assertEquals(2, list.findNthNumberAfterZero(3000).getValue());

        assertEquals(3, list.findNthNumberAfterZero(1000).getValue() + list.findNthNumberAfterZero(2000).getValue() +list.findNthNumberAfterZero(3000).getValue());
    }

    @Test
    void testActualEncryptedNumber() throws IOException {
        File input = new ClassPathResource("2022/EncryptedNumbersInput.txt").getFile();
        EncryptedNumbersList list = new EncryptedNumbersFactory().generateFromFile(input);
        list.decrypt();

        assertEquals(17490, list.findNthNumberAfterZero(1000).getValue() + list.findNthNumberAfterZero(2000).getValue() +list.findNthNumberAfterZero(3000).getValue());
    }
}
