package nl.roykovic.aoc.signalstrength;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
    }
    @Test
    void testSmallExampleRegisterIncrease() throws IOException {
        File input = new File("src/test/resources/SignalStrengthSmallProgramInput.txt");
        Program program = new CycleFactory().generateFromFile(input);

       Map<Integer, Integer> steps = program.run();


       assertEquals(6, steps.size());   //the test should have 6 steps
       assertEquals(-1, steps.get(6));  //X should be -1 on step 6
    }

    @Test
    void testLargeExampleSignalStrength() throws IOException {
        File input = new File("src/test/resources/SignalStrengthLargeProgramInput.txt");
        Program program = new CycleFactory().generateFromFile(input);

        Map<Integer, Integer> steps = program.run();

        System.out.println(steps);

        assertEquals(21, steps.get(20)); //X is 21 on step 20 etc
        assertEquals(19, steps.get(60));
        assertEquals(18, steps.get(100));
        assertEquals(21, steps.get(140));
        assertEquals(16, steps.get(180));
        assertEquals(18, steps.get(220));

        assertEquals(13140,program.getSignalStrengthByCycles(20,60,100,140,180,220)); //sum of signalstrengths should be 13140
    }

    @Test
    void testActualSignalStrength() throws IOException {
        File input = new ClassPathResource("SignalStrengthInput.txt").getFile();
        Program program = new CycleFactory().generateFromFile(input);

        program.run(); //run the program to fill the steps array
        int sum = program.getSignalStrengthByCycles(20,60,100,140,180,220); //get (and sum) the signalstrengths from these cycles

        assertEquals(13760, sum);   //answer to day10 part 1
    }

    @Test
    void testLargeExampleCRTOutput() throws IOException {
        File input = new File("src/test/resources/SignalStrengthLargeProgramInput.txt");
        Program program = new CycleFactory().generateFromFile(input);

        program.run(); //run the program to print CRT output
        assertEquals(
                """
                        ##..##..##..##..##..##..##..##..##..##..\r
                        ###...###...###...###...###...###...###.\r
                        ####....####....####....####....####....\r
                        #####.....#####.....#####.....#####.....\r
                        ######......######......######......####\r
                        #######.......#######.......#######.....\r
                        """, outContent.toString()); //this is the output the example should give
    }

    @Test
    void testActualCRTOutput() throws IOException {
        File input = new ClassPathResource("SignalStrengthInput.txt").getFile();
        Program program = new CycleFactory().generateFromFile(input);

        program.run(); //run the program to print CRT output
        assertEquals(
                """
                        ###..####.#..#.####..##..###..####.####.\r
                        #..#.#....#.#.....#.#..#.#..#.#....#....\r
                        #..#.###..##.....#..#....#..#.###..###..\r
                        ###..#....#.#...#...#....###..#....#....\r
                        #.#..#....#.#..#....#..#.#....#....#....\r
                        #..#.#....#..#.####..##..#....####.#....\r
                        """, outContent.toString()); //this is the output the example should give (reading RFKZCPEF)
    }

}
