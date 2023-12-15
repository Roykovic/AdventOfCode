package nl.roykovic.aoc._2023.day10_pipes;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.FileReaderService;
import nl.roykovic.aoc.utils.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PipesFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "PipesTestInputOne.txt,true,4",
            "PipesTestInputTwo.txt,true,4",
            "PipesTestInputThree.txt,true,8",
            "PipesTestInputFour.txt,true,8",
            "PipesInput.txt,false,6947"
    })
    public void testStepsToFurthestPoint(String filename, boolean test, int expected) {
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new PipesFactory().generate(input);

        long furthestSteps =output.values().stream().mapToLong(Pipe::getStepsFromStart).max().orElseThrow();

        //since the loop is circular, the furthest step is always the neighbour. So the actual furthest point, sits in the middle. Hence;
        int actualFurthestSteps = (int) Math.ceil((double) furthestSteps /2);

        assertEquals(expected, actualFurthestSteps);
    }

    @ParameterizedTest
    @CsvSource({
            "PipesTestInputFive.txt,true,4",
            "PipesTestInputSix.txt,true,4",
            "PipesTestInputSeven.txt,true,8",
            "PipesTestInputEight.txt,true,10",
            "PipesInput.txt,false,273"
    })
    public void testAreaBetweenPipes(String filename, boolean test, int expected) {
        //I cheated https://www.reddit.com/r/adventofcode/comments/18evyu9/comment/kcqmhwk/?utm_source=share&utm_medium=web3x&utm_name=web3xcss&utm_term=1&utm_content=share_button

        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new PipesFactory().generate(input);

        //We are going to use Picks theorem https://en.wikipedia.org/wiki/Pick%27s_theorem
        //Picks theorem describes                               area = interiorPoints + (boundaryPoints/2) -1
        //But we need the interior points, so:
        //By subtracting the area from both sides we get        0 = interiorPoints + (boundaryPoints/2) -1 -area
        //Now by subtracting interior points from both sides:   -interiorPoints = (boundaryPoints/2) -1 -area
        //So                                                    interiorPoints = |((boundaryPoints/2) -1 -area)|
        //
        //We have the boundary points, which is simply all the points from the closed loop so output.size()
        //We only need the area. We can calculate this, by using the shoelace theorem. This needs the points in a clockwise manner,
        //Luckily we already did that in the pipes-factory, so:
        var area = Utils.shoelaceArea(output.keySet().stream().toList());

        //So now the interiorPoints can be calculated as:

        //                   |                   boundaryPoints/2  -1 -area|
        var interiorPoints = Math.abs(((double) output.size() /2) -1 -area);

        assertEquals(expected,interiorPoints);
    }
}