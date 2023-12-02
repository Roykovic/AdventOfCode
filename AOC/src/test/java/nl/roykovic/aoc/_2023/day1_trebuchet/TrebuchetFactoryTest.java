package nl.roykovic.aoc._2023.day1_trebuchet;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TrebuchetFactoryTest {

    @Test
    void testExampleCalibration() throws IOException {
        File input = new File("src/test/resources/2023/TrebuchetTestInput.txt");
        IntStream stream = new TrebuchetFactory().generateFromFile(input, false);

        assertEquals(142, stream.sum());
    }

    @Test
    void testActualCalibration() throws IOException {
        File input = new ClassPathResource("2023/TrebuchetInput.txt").getFile();
        IntStream stream = new TrebuchetFactory().generateFromFile(input, false);

        assertEquals(54390, stream.sum());
    }

}
