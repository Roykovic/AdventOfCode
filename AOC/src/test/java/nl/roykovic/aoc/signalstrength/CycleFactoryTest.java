package nl.roykovic.aoc.signalstrength;

import nl.roykovic.aoc.rucksack.Rucksack;
import nl.roykovic.aoc.rucksack.RucksackFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CycleFactoryTest {
    @Test
    void testSmallExampleRegisterIncrease() throws IOException {
        File input = new File("src/test/resources/SignalStrengthSmallProgramInput.txt");
        Program program = new CycleFactory().generateFromFile(input);

       Map<Integer, Integer> steps = program.run();

       assertEquals(5, steps.size());   //the test should have 5 steps
       assertEquals(-1, steps.get(5));  //X should be -1 on step 5
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

}
