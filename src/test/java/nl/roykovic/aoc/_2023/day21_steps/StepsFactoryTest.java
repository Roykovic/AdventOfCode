package nl.roykovic.aoc._2023.day21_steps;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StepsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "StepsTestInput.txt,true,6,16",
            "StepsTestInput.txt,true,10,50",
            "StepsTestInput.txt,true,50,1594",
            "StepsTestInput.txt,true,100,6536",
            "StepsTestInput.txt,true,500,167004",
            "StepsTestInput.txt,true,1000,668697",
            "StepsTestInput.txt,true,5000,16733044",
            "StepsInput.txt,false,64,3782",
//            "StepsInput.txt,false,26501365,-1",
    })
    public void test(String filename, boolean test, int steps, int expected) {
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new StepsFactory().generate(input, steps);

        assertEquals(expected, output);
    }

}
