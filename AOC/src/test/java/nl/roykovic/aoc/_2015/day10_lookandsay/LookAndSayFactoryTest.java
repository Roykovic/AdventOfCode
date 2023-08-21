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

    @ParameterizedTest
    @CsvSource(value = {
            "1321131112, 40, 492982",
            "1321131112, 50, 6989950",
    })
    void testActualLookAndSay(String line, int times, int expectedLength){

        LookAndSayFactory factory = new LookAndSayFactory();
        String input = line;
        for(int i = 0; i < times; i++){
            input = factory.lookAndSay(input);
        }

        assertEquals(expectedLength, input.length());
    }
}
