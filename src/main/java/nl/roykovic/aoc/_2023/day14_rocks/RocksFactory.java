package nl.roykovic.aoc._2023.day14_rocks;

import nl.roykovic.aoc.utils.Memoizer;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RocksFactory {
    public long generate(String input, boolean cycles) {
        var inputGrid = Arrays
                .stream(input.split("\\r\\n+"))
                .map(String::toCharArray).toArray(char[][]::new);

        if(cycles){
            Map<String, Long> previousGrids= new LinkedHashMap<>();

            int cycleBegin = -1;
            int cycleLength = -1;

            int index = 0;
            while(cycleBegin == -1 && cycleLength == -1){
                inputGrid = tiltAllDirections(inputGrid);

                String stringifiedGrid = Arrays.deepToString(inputGrid); //stringify the grid to cache it.

                if(previousGrids.containsKey(stringifiedGrid)){
                    cycleBegin = new ArrayList<>(previousGrids.keySet()).indexOf(stringifiedGrid);
                    cycleLength = index-cycleBegin;
                }
                previousGrids.put(stringifiedGrid, getLoad(inputGrid));
                index++;
            }

            int remainder = (1000000000 - cycleBegin) % cycleLength; //this is the number of tiltsInAllDirections we have to do after doing ((1000000000 - cycleBegin) / cycleLength) cycles
            return (long) previousGrids.values().toArray()[remainder+cycleBegin-1]; //now get the remainder-th value from the array (disregarding the steps we had to do before we entered a cycle, and -1 to convert it to 0 based index
        }
        else {
            inputGrid = tiltGrid(inputGrid);
        }

        return getLoad(inputGrid);
    }

    private char[][] tiltAllDirections(char[][] input){
        input = tiltGrid(input);
        input = rotateGridClockwise(input);
        input = tiltGrid(input);
        input = rotateGridClockwise(input);
        input = tiltGrid(input);
        input = rotateGridClockwise(input);
        input = tiltGrid(input);
        input = rotateGridClockwise(input);

        return input;
    }

    private char[][] tiltGrid(char[][] grid) {
        //We rotate the array anti-clockwise, so we can look by row instead of by column
        var rotatedGrid = rotateGridAntiClockwise(grid);

        var tiltedGrid =  Arrays.stream(rotatedGrid).map(line -> {
            String verticalString = new String(line);

            var movedRocks = Arrays.stream(verticalString.split("#")).map(it -> {

                String rocks = StringUtils.repeat("O", StringUtils.countMatches(it, "O"));
                String emptySpaces = StringUtils.repeat(".", it.length() - rocks.length());

                return rocks + emptySpaces;
            }).collect(Collectors.joining("#"));

            //if there is # at the end, they have been cut off now. So we add them back
            String end = StringUtils.repeat("#", line.length - movedRocks.length());
            var out = movedRocks + end;

            return out.toCharArray();
        }).toArray(char[][]::new);

        //After doing the tilt, we rotate the array back
        return  rotateGridClockwise(tiltedGrid);
    }

    private char[][] rotateGridClockwise(char[][] input) {
        char[][] output = new char[input[0].length][input.length];

        for (int y = 0; y < output.length; y++) {
            for (int x = 0; x < output[y].length; x++) {
                output[y][x] = input[(output[y].length - 1) - x][y];
            }
        }

        return output;
    }

    private char[][] rotateGridAntiClockwise(char[][] input) {
        char[][] output = new char[input[0].length][input.length];

        for (int y = 0; y < input[0].length; y++) {
            for (int x = input.length - 1; x >= 0; x--) {
                output[y][x] = input[x][(output[y].length - 1) - y];
            }
        }
        return output;
    }

    private long getLoad(char[][] inputGrid){
        long sum = 0;
        //count all the O's in a row, adding the amount of matches * the row+1
        for (int i = 0; i < inputGrid.length; i++) {
            String currentString = Arrays.toString(inputGrid[inputGrid.length - 1 - i]);
            sum += (long) StringUtils.countMatches(currentString, 'O') * (i + 1);
        }
        return sum;
    }
}
