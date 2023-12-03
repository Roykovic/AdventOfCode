package nl.roykovic.aoc._2015.day19_molecules;

import nl.roykovic.aoc._2015.day18_lightsparttwo.LightFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoleculeFactoryTest {
    @Test
    void testExampleCalibration() throws IOException {
        File input = new File("src/test/resources/2015/MoleculeExampleInput.txt");
        int combinations = new MoleculeFactory().generateFromFile(input);

        assertEquals(4, combinations);
    }

    @Test
    void testExampleCalibrationSecondExample() throws IOException {
        File input = new File("src/test/resources/2015/MoleculeSecondExampleInput.txt");
        int combinations = new MoleculeFactory().generateFromFile(input);

        assertEquals(7, combinations);
    }

    @Test
    void testActualCalibration() throws IOException {
        File input = new ClassPathResource("2015/MoleculeInput.txt").getFile();
        int combinations = new MoleculeFactory().generateFromFile(input);

        assertEquals(4, combinations);
    }
}
