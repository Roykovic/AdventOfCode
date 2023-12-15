package nl.roykovic.aoc._2022.day22_monkeymap;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyMapFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "MonkeyMapTestInput.txt,true,6032",
            "MonkeyMapInput.txt,false,58248"
    })
    void testRoute(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        MonkeyMap monkeyMap = new MonkeyMapFactory().generateFromFile(input);

        monkeyMap.move();

        assertEquals(expected, 1000*(monkeyMap.getCurrentY()+1) + 4* (monkeyMap.getCurrentX()+1) + monkeyMap.getCurrentDirection());
    }
}
