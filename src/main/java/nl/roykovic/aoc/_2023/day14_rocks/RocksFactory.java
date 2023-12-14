package nl.roykovic.aoc._2023.day14_rocks;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RocksFactory {
    public long generate(String input) {
        var inputGrid = Arrays
                .stream(input.split("\\r\\n+"))
                .map(String::toCharArray).toArray(char[][]::new);

        inputGrid = tiltGrid(inputGrid);

        long sum = 0;
        //count all the O's in a row, adding the amount of matches * the row+1
        for (int i = 0; i < inputGrid.length; i++) {
            String currentString = Arrays.toString(inputGrid[inputGrid.length - 1 - i]);
            sum += (long) StringUtils.countMatches(currentString, 'O') * (i + 1);
        }

        return sum;
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

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                output[i][j] = input[(output[i].length - 1) - j][i];
            }
        }

        return output;
    }

    private char[][] rotateGridAntiClockwise(char[][] input) {
        char[][] output = new char[input[0].length][input.length];

        for (int i = 0; i < input[0].length; i++) {
            for (int j = input.length - 1; j >= 0; j--) {
                output[i][j] = input[j][i];
            }
        }
        return output;
    }
}
