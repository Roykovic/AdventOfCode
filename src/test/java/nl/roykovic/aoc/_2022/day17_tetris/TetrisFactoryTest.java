package nl.roykovic.aoc._2022.day17_tetris;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TetrisFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "TetrisTestInput.txt,true,2022,3068",
            "TetrisInput.txt,false,2022,3151",
//            "TetrisTestInput.txt,true,1000000000000,-1",
//            "TetrisInput.txt,false,1000000000000,-1"
    })
    void testHeight(String filename, boolean test,long rounds, long expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        assertEquals(expected, new TetrisFactory().generateFromFile(input, rounds));
    }
}
