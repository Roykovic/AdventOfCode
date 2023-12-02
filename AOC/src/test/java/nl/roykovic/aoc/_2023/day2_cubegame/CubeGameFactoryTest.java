package nl.roykovic.aoc._2023.day2_cubegame;

import nl.roykovic.aoc._2023.day1_trebuchet.TrebuchetFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CubeGameFactoryTest {
    @Test
    void testExampleCubeGame() throws IOException {
        File input = new File("src/test/resources/2023/CubeGameTestInput.txt");
        int ans = new CubeGameFactory().generateFromFile(input);

        assertEquals(8, ans);
    }
}
