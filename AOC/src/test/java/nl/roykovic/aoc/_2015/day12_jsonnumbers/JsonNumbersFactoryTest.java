package nl.roykovic.aoc._2015.day12_jsonnumbers;

import nl.roykovic.aoc._2015.day5_naughtystrings.NaughtyOrNiceFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonNumbersFactoryTest{
        @Test
        void testActualSum() throws IOException {
            File input = new ClassPathResource("2015/JsonNumbersInput.txt").getFile();
            int sum = new JsonNumbersFactory().generateFromFile(input);
            System.out.println(sum);
        }
}

