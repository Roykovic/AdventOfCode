package nl.roykovic.aoc._2015.day10_lookandsay;

import nl.roykovic.aoc._2015.day8_memoryusage.MemoryUsageFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LookAndSayFactoryTest {
    @ParameterizedTest
    @CsvSource(value = {
            "1, 11",
            "11, 21",
            "21, 1211",
            "1211, 111221",
            "111221, 312211",
    })
    void testExampleLookAndSay(String line, String answer){
        String actualAnswer = new LookAndSayFactory().lookAndSay(line);

        assertEquals(answer, actualAnswer);
    }

    @Test
    void testActualLookAndSay(){

        LookAndSayFactory factory = new LookAndSayFactory();
        String input = "1321131112";
        int times = 40;
        for(int i = 0; i < times; i++){
            input = factory.lookAndSay(input);
        }

        assertEquals(492982, input.length());
    }
}
