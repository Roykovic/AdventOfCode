package nl.roykovic.aoc._2015.day8_memoryusage;

import nl.roykovic.aoc._2015.day7_logicgates.LogicGateFactory;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
    void testExampleMemoryUsage(String line, int code, int data) throws IOException {
        Long memoryDif = new MemoryUsageFactory().getMemoryDifFromList(List.of(line));

        assertEquals(code - data, memoryDif);
    }

    @Test
    void testActualMemoryUsage() throws IOException {
        File input = new ClassPathResource("2015/MemoryUsageInput.txt").getFile();
        Long memoryDif = new MemoryUsageFactory().generateFromFile(input);
        assertEquals(1350, memoryDif);
    }
