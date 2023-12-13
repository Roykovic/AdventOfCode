package nl.roykovic.aoc._2023.day10_pipes;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.FileReaderService;
import nl.roykovic.aoc.utils.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PipesFactoryTest {

    private List<Coord> visited = new ArrayList<>();

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
    public void testAreaBetweenPipes(String filename, boolean test, int expected) {
        //I cheated https://www.reddit.com/r/adventofcode/comments/18evyu9/comment/kcqmhwk/?utm_source=share&utm_medium=web3x&utm_name=web3xcss&utm_term=1&utm_content=share_button

        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        var output = new PipesFactory().generate(input);
        var area = Utils.shoelaceArea(output.keySet().stream().toList());

        //https://en.wikipedia.org/wiki/Pick%27s_theorem -> area = interiorPoints + (boundaryPoints/2) -1
        //So 0 = interiorPoints + (boundaryPoints/2) -1 -area
        // interiorPoints = (boundaryPoints/2) -1 -area

        var interiorPoints = (output.values().size() /2) -1 -area;

        System.out.println(interiorPoints);
    }


    private void drawPipes(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    private void drawPipes(Map<Coord, Pipe> output) {
        long minX = output.values().stream().mapToLong(it -> it.getCoord().getX()).min().orElseThrow();
        long maxX = output.values().stream().mapToLong(it -> it.getCoord().getX()).max().orElseThrow();

        long minY = output.values().stream().mapToLong(it -> it.getCoord().getY()).min().orElseThrow();
        long maxY = output.values().stream().mapToLong(it -> it.getCoord().getY()).max().orElseThrow();

        for (long y = minY; y <= maxY; y++) {
            for (long x = minX; x <= maxX; x++) {
                Pipe currPipe = output.getOrDefault(new Coord(x, y), null);
                if (currPipe != null) {
                    System.out.print(currPipe.getPipeCharStylized());
                } else {
                    System.out.print(".");
                }
            }

            System.out.println("");
        }
    }

    private void floodFillOutside(char[][] grid, Coord coord) {
        if (visited.contains(coord)) {
            return;
        }
        visited.add(coord);
        if (coord.getY() >= 0 && coord.getX() >= 0 && coord.getY() < grid.length && coord.getX() < grid[0].length) {
            char curChar = grid[coord.getY().intValue()][coord.getX().intValue()];
            if (curChar == 'I') {
                grid[coord.getY().intValue()][coord.getX().intValue()] = 'O';

                List<Coord> neighbours = coord.getNeighbours().stream().filter(it -> !visited.contains(it)).toList();
                for (Coord neighbour : neighbours) {
                    floodFillOutside(grid, neighbour);
                }

            }
        }
    }

    private void floodFillInside(char[][] grid) {

        List<Coord> unfilledCoords = new ArrayList<>();
        int counter = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if(grid[y][x] == 'I'){
                    unfilledCoords.add(new Coord(x, y));
                }
            }
        }

//        for(Coord coord: unfilledCoords){
//            Boolean found
//        }

    }
}