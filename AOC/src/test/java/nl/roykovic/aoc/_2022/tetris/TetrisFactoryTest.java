package nl.roykovic.aoc._2022.tetris;

import nl.roykovic.aoc._2022.rucksack.Rucksack;
import nl.roykovic.aoc._2022.rucksack.RucksackFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TetrisFactoryTest {
    @Test
    void testExampleHeight() throws IOException {
        File input = new File("src/test/resources/2022/TetrisTestInput.txt");
        assertEquals(3068, new TetrisFactory().generateFromFile(input, 2022));
    }

    @Test
    void testActualHeight() throws IOException {
        File input = new ClassPathResource("2022/TetrisInput.txt").getFile();
        assertEquals(3151, new TetrisFactory().generateFromFile(input, 2022));
    }

    @Disabled
    @Test
    void testExampleHeightTrillionRounds() throws IOException {
        File input = new File("src/test/resources/2022/TetrisTestInput.txt");
        assertEquals(3068, new TetrisFactory().generateFromFile(input, 10000000));
    }

    @Disabled
    @Test
    void testActualHeightTrillionRounds() throws IOException {
        File input = new ClassPathResource("2022/TetrisInput.txt").getFile();
        assertEquals(3151, new TetrisFactory().generateFromFile(input, 1000000000000L));
    }
}
