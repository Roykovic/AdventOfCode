package nl.roykovic.aoc._2022.day10_signalstrength;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CycleFactoryTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testSmallExampleRegisterIncrease() {
        var input = FileReaderService.getLinesFromFile(2022, "SignalStrengthSmallProgramInput.txt", true);
        Program program = new CycleFactory().generateFromFile(input);

        Map<Integer, Integer> steps = program.run();

        assertEquals(6, steps.size());   //the test should have 6 steps
        assertEquals(-1, steps.get(6));  //X should be -1 on step 6
    }

    @ParameterizedTest
    @CsvSource({
            "SignalStrengthLargeProgramInput.txt,true,13140",
            "SignalStrengthInput.txt,false,13760"
    })
    void testSignalStrength(String filename, boolean test, int expected) {
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        Program program = new CycleFactory().generateFromFile(input);

        program.run(); //run the program to fill the steps array
        int sum = program.getSignalStrengthByCycles(20, 60, 100, 140, 180, 220); //get (and sum) the signalstrengths from these cycles

        assertEquals(expected, sum);
    }

    @ParameterizedTest
    @CsvSource({
            "SignalStrengthLargeProgramInput.txt,true,0",
            "SignalStrengthInput.txt,false,1"
    })
    void testCRTOutput(String filename, boolean test, int expected) {

        String[] expectedAnswers = new String[]{"""
                        ##..##..##..##..##..##..##..##..##..##..\r
                        ###...###...###...###...###...###...###.\r
                        ####....####....####....####....####....\r
                        #####.....#####.....#####.....#####.....\r
                        ######......######......######......####\r
                        #######.......#######.......#######.....\r
                        """, """
                        ###..####.#..#.####..##..###..####.####.\r
                        #..#.#....#.#.....#.#..#.#..#.#....#....\r
                        #..#.###..##.....#..#....#..#.###..###..\r
                        ###..#....#.#...#...#....###..#....#....\r
                        #.#..#....#.#..#....#..#.#....#....#....\r
                        #..#.#....#..#.####..##..#....####.#....\r
                        """};

        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        Program program = new CycleFactory().generateFromFile(input);

        program.run(); //run the program to print CRT output
        assertEquals(expectedAnswers[expected], outContent.toString()); //this is the output the example should give
    }

}
