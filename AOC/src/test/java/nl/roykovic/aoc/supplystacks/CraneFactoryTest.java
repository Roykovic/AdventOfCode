package nl.roykovic.aoc.supplystacks;

import nl.roykovic.aoc.supplystacks.Crane;
import nl.roykovic.aoc.supplystacks.CraneFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class CraneFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("CrateInput.txt").getFile();
        Crane crane = new CraneFactory().generateFromFile(input);

        crane.executeInstructions();

        crane.printTopCrates();

    }
    @Test
    void testFactory2() throws IOException {
        File input = new ClassPathResource("CrateInput.txt").getFile();
        Crane crane = new CraneFactory().generateFromFile(input);

        crane.executeImprovedInstructions();

        crane.printTopCrates();

    }

}
