package nl.roykovic.aoc._2023.day10_pipes;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PipesFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "PipesTestInputOne.txt,true,4",
            "PipesTestInputTwo.txt,true,4",
            "PipesTestInputThree.txt,true,8",
            "PipesTestInputFour.txt,true,8",
            "PipesInput.txt,false,-1"
    })
    public void testStepsToFurthestPoint(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new PipesFactory().generate(input);

        assertEquals(expected, output);
    }
}
