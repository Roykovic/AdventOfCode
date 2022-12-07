package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.SupplyStacks.Crane;
import nl.roykovic.AOC.domain.SupplyStacks.CraneFactory;
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
