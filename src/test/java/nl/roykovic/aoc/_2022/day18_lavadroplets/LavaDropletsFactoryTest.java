package nl.roykovic.aoc._2022.day18_lavadroplets;

import nl.roykovic.aoc.utils.Face;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LavaDropletsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "LavaDropletsTestInput.txt,true,64",
            "LavaDropletsInput.txt,false,4308"
    })
    void testSurfaceArea(String filename, boolean test, long expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        List<Face> faces = new LavaDropletsFactory().generateFromFile(input);

        assertEquals(expected, faces.size());
    }
}
