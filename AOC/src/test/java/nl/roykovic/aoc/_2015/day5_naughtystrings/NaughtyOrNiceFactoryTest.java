package nl.roykovic.aoc._2015.day5_naughtystrings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NaughtyOrNiceFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "ugknbfddgicrmopn,true",
            "aaa,true",
            "jchzalrnumimnmhp,false",
            "haegwjzuvuyypxyu, false",
            "dvszwmarrgswjxmb, false"
    })
    void testExampleIsNice(String input, boolean expectedIsNice) {
        assertEquals(expectedIsNice, NaughtyOrNiceFactory.isNice(input));
    }
    @Test
    void testActualIsNice() throws IOException {
        File input = new ClassPathResource("2015/NaughtyOrNiceInput.txt").getFile();
        long niceStrings = new NaughtyOrNiceFactory().generateFromFile(input).values().stream().filter(it -> it).count();
        assertEquals(258, niceStrings);
    }

    @ParameterizedTest
    @CsvSource({
            "qjhvhtzxzqqjkmpb,true",
            "xxyxx,true",
            "uurcxstgmygtbstg,false",
            "ieodomkazucvgmuy, false"
    })
    void testExampleNewIsNice(String input, boolean expectedIsNice) {
        assertEquals(expectedIsNice, NaughtyOrNiceFactory.newIsNice(input));
    }
    @Test
    void testActualNewIsNice() throws IOException {
        File input = new ClassPathResource("2015/NaughtyOrNiceInput.txt").getFile();
        long niceStrings = new NaughtyOrNiceFactory().generateNewFromFile(input).values().stream().filter(it -> it).count();
        assertEquals(53, niceStrings);
    }
}
