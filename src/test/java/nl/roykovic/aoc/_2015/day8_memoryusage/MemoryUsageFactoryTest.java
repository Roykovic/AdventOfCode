package nl.roykovic.aoc._2015.day8_memoryusage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryUsageFactoryTest {
    @ParameterizedTest
    @CsvSource(value = {
            "\"\", 2, 0",
            "\"abc\", 5, 3",
            "\"aaa\\\"aaa\", 10, 7",
            "\"\\x27\", 6, 1",
    })
    void testExampleMemoryUsage(String line, int code, int data) {
        Long memoryDif = new MemoryUsageFactory().getMemoryDifFromList(List.of(line));

        assertEquals(code - data, memoryDif);
    }

    @Test
    void testActualMemoryUsage() throws IOException {
        File input = new ClassPathResource("2015/MemoryUsageInput.txt").getFile();
        Long memoryDif = new MemoryUsageFactory().generateFromFile(input);
        assertEquals(1350, memoryDif);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "\"\", 6, 2",
            "\"abc\", 9, 5",
            "\"aaa\\\"aaa\", 16, 10",
            "\"\\x27\", 11, 6",
    })
    void testExampleMemoryUsageOtherWay(String line, int code, int data) {

        Long memoryDif = new MemoryUsageFactory().getMemoryDifOtherWay(List.of(line));

        assertEquals(code - data, memoryDif);
    }

    @Test
    void testActualMemoryUsageOtherWay() throws IOException {
        File input = new ClassPathResource("2015/MemoryUsageInput.txt").getFile();
        Long memoryDif = new MemoryUsageFactory().generateFromFileOtherWay(input);
        assertEquals(2085, memoryDif);
    }

}
