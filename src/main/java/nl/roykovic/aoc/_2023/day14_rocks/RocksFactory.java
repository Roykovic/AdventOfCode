package nl.roykovic.aoc._2023.day14_rocks;

import nl.roykovic.aoc.utils.Memoizer;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
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
            for(int i = 0; i < 1000000000; i++){
                inputGrid = tiltCycleCached.apply(inputGrid);
            }
        }
        else {
            inputGrid = tiltGrid(inputGrid);
        }

        long sum = 0;
        //count all the O's in a row, adding the amount of matches * the row+1
        for (int i = 0; i < inputGrid.length; i++) {
            String currentString = Arrays.toString(inputGrid[inputGrid.length - 1 - i]);
            sum += (long) StringUtils.countMatches(currentString, 'O') * (i + 1);
        }

        return sum;
    }

    Function<char[][],char[][] > tiltCycleCached =
            Memoizer.memoize(this::tiltCycle);

    Function<char[][],char[][] > tiltGridCached =
            Memoizer.memoize(this::tiltGrid);

    private char[][] tiltCycle(char[][] input){
        input = tiltGridCached.apply(input);
        input = rotateGridClockwise(input);
        input = tiltGridCached.apply(input);
        input = rotateGridClockwise(input);
        input = tiltGridCached.apply(input);
        input = rotateGridClockwise(input);
        input = tiltGridCached.apply(input);


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
}
