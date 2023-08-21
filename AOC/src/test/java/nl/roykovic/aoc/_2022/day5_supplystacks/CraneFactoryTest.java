package nl.roykovic.aoc._2022.day5_supplystacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CraneFactoryTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
    }
    @Test
    void test9000CrateExampleOutput() throws IOException {
        File input = new File("src/test/resources/2022/CrateTestInput.txt");
        Crane crane = new CraneFactory().generateFromFile(input);

        crane.executeInstructions();

        crane.printTopCrates();
        assertEquals("CMZ", outContent.toString());
    }

    @Test
    void test9000CrateActualOutput() throws IOException {
        File input = new ClassPathResource("2022/CrateInput.txt").getFile();
        Crane crane = new CraneFactory().generateFromFile(input);

        crane.executeInstructions();

        crane.printTopCrates();
        assertEquals("RTGWZTHLD", outContent.toString());

    }
    @Test
    void test9001CrateExampleOutput() throws IOException {
        File input = new File("src/test/resources/2022/CrateTestInput.txt");
        Crane crane = new CraneFactory().generateFromFile(input);

        crane.executeImprovedInstructions();

        crane.printTopCrates();
        assertEquals("MCD", outContent.toString());
    }

    @Test
    void test9001CrateActualOutput() throws IOException {
        File input = new ClassPathResource("2022/CrateInput.txt").getFile();
        Crane crane = new CraneFactory().generateFromFile(input);

        crane.executeImprovedInstructions();

        crane.printTopCrates();
        assertEquals("STHGRZZFR", outContent.toString());
    }

}
