package nl.roykovic.aoc._2015.day7_logicgates;

import nl.roykovic.aoc._2015.day6_lights.LightFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicGateFactoryTest {
    @Test
    void testActualGateValues() throws IOException {
        File input = new ClassPathResource("2015/LogicGateInput.txt").getFile();
        Short valueOfA = new LogicGateFactory().generateFromFile(input, "a");

        assertEquals((short)16076, valueOfA);
    }
}
