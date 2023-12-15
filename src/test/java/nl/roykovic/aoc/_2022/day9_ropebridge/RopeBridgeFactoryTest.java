package nl.roykovic.aoc._2022.day9_ropebridge;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RopeBridgeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "RopeBridgeTestInput.txt,true,1,13",
            "RopeBridgeInput.txt,false,1,5883",
            "RopeBridgeTestInput.txt,true,9,1",
            "RopeBridgeLongRopeTestInput.txt,true,9,36",
            "RopeBridgeInput.txt,false,9,2367"
    })
    void testLocationsVisited(String filename, boolean test, int tails, int expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        List<Map.Entry<Integer, Integer>> path = new RopeBridgeFactory().generateFromFile(input, tails);

        int distinctPathSize = path.stream().distinct().toList().size();
        assertEquals(expected, distinctPathSize);
    }
}
