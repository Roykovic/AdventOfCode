package nl.roykovic.aoc._2023.day10_pipes;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    public void testStepsToFurthestPoint(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new PipesFactory().generate(input);

        assertEquals(expected, output.values().stream().mapToLong(Pipe::getStepsFromStart).max().orElseThrow());
    }

    @ParameterizedTest
    @CsvSource({
            "PipesTestInputFive.txt,true,4",
            "PipesTestInputSix.txt,true,4",
            "PipesTestInputSeven.txt,true,8",
            "PipesTestInputEight.txt,true,10",
            "PipesInput.txt,false,6947"
    })
    public void testAreaBetweenPipes(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new PipesFactory().generate(input);

        System.out.println();
        System.out.println();
        System.out.println();
        drawPipes(output);
    }


    private void drawPipes(Map<Coord, Pipe> output) {
        long minX = output.values().stream().mapToLong(it -> it.getCoord().getX()).min().orElseThrow();
        long maxX = output.values().stream().mapToLong(it -> it.getCoord().getX()).max().orElseThrow();

        long minY = output.values().stream().mapToLong(it -> it.getCoord().getY()).min().orElseThrow();
        long maxY = output.values().stream().mapToLong(it -> it.getCoord().getY()).max().orElseThrow();

        for(long y = minY; y <= maxY; y++){
            for(long x = minX; x<= maxX; x++) {
                Pipe currPipe = output.getOrDefault(new Coord(x, y), null);
                if(currPipe != null){
                    System.out.print(currPipe.getPipeCharStylized());
                }
                else {
                    System.out.print(".");
                }
            }

            System.out.println("");
        }
    }
}
