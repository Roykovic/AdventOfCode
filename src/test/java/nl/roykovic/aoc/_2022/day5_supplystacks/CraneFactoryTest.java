package nl.roykovic.aoc._2022.day5_supplystacks;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CraneFactoryTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
    }
    @ParameterizedTest
    @CsvSource({
            "CrateTestInput.txt,true,CMZ",
            "CrateInput.txt,false,RTGWZTHLD",
    })
    void test9000CrateOutput(String filename, boolean test, String expected) {
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        Crane crane = new CraneFactory().generateFromFile(input);

        crane.executeInstructions();
        crane.printTopCrates();

        assertEquals(expected, outContent.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "CrateTestInput.txt,true,MCD",
            "CrateInput.txt,false,STHGRZZFR",
    })
    void test9001CrateOutput(String filename, boolean test, String expected) {
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        Crane crane = new CraneFactory().generateFromFile(input);

        crane.executeImprovedInstructions();

        crane.printTopCrates();
        assertEquals(expected, outContent.toString());
    }

}
