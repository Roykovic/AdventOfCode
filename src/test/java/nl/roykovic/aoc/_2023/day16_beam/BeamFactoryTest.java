package nl.roykovic.aoc._2023.day16_beam;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BeamFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "BeamTestInput.txt,true,46",
            "BeamTestInputTSplit.txt,true,9",
            "BeamTestInputDiagonal.txt,true,18",
            "BeamTestInputInfiniteLoop.txt,true,16",
            "BeamTestInputMultipleLoops.txt,true,41",
            "BeamTestInputForTypo.txt,true,89",
            "BeamInput.txt,false,7623", //7232 is too low
    })
    public void testInitialSetup(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2023, filename, test);
        var output = new BeamFactory().generate(input);

        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "BeamTestInput.txt,true,51",
            "BeamInput.txt,false,7623"
    })
    public void testOptimalSetup(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2023, filename, test);
        var inputGrid = Arrays
                .stream(input.split("\\r\\n+"))
                .map(String::toCharArray).toArray(char[][]::new);

        List<Map.Entry<Coord, Direction>> startPoints = new ArrayList<>();

        for(int y = 0; y< inputGrid.length; y++){
            Coord leftBorder = new Coord(-1,y);
            Coord rightBorder = new Coord(inputGrid[y].length,y);

            startPoints.add(Map.entry(leftBorder, Direction.R));
            startPoints.add(Map.entry(rightBorder, Direction.L));
        }

        for(int x = 0; x< inputGrid[0].length; x++){
            Coord topBorder = new Coord(x,-1);
            Coord bottomBorder = new Coord(x,inputGrid.length);

            startPoints.add(Map.entry(topBorder,Direction.D));
            startPoints.add(Map.entry(bottomBorder,Direction.U));
        }

        var output = startPoints.stream().mapToInt(it -> new BeamFactory().generate(inputGrid,it.getKey(), it.getValue())).max().orElseThrow();

        assertEquals(expected, output);
    }}
