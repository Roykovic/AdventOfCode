package nl.roykovic.aoc._2015.day19_molecules;

import nl.roykovic.aoc._2015.day15_ingredients.IngredientsFactory;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
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

        assert (combinations < 603); //603 is wrong; answer is too high
        assert (combinations < 581); //581 is wrong; answer is too high
        assertEquals(509, combinations);
    }

    @ParameterizedTest
    @CsvSource({
            "MoleculeExampleInput.txt,true,3",
            "MoleculeSecondExampleInput.txt,true,6",
            "MoleculeInput.txt,false,-1",
    })
    public void testFabrication(String filename, boolean test, int expected) {
        var input = FileReaderService.getLinesFromFile(2015, filename, test);
        var output = new MoleculeFactory().generateNodesFromFile(input);

        assert(output < 467); //answer too high
        assertEquals(expected, output);
    }
}
