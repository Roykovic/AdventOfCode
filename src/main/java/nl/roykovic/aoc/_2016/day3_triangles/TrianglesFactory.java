package nl.roykovic.aoc._2016.day3_triangles;

import java.util.Arrays;
import java.util.stream.Stream;
public class TrianglesFactory {
    public int generate(Stream<String> input) {
        return (int) input
                .map(String::trim)
                .map(it -> it.split(" "))
                .map(it -> Arrays.stream(it).filter(s -> !s.isEmpty()).toArray(String[]::new))
                .map(this::isValid).filter(it -> it).count();
    }

    public int generateByColumn(Stream<String> input){
        var grid =input
                .map(String::trim)
                .map(it -> it.split(" "))
                .map(it -> Arrays.stream(it).filter(s -> !s.isEmpty()).toArray(String[]::new))
                .toArray(String[][]::new);

        int counter = 0;

        for(int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y + 2 < grid.length; y+=3) {

                String[] curArr = new String[3];

                curArr[0] = grid[y][x];
                curArr[1] = grid[y+1][x];
                curArr[2] = grid[y+2][x];

                if(isValid(curArr)){
                    counter++;
                }

            }
        }


        return counter;
    }

    private boolean isValid(String[] triangleArray){
        int firstSide = Integer.parseInt(triangleArray[0]);
        int secondSide = Integer.parseInt(triangleArray[1]);
        int thirdSide = Integer.parseInt(triangleArray[2]);

        return firstSide + secondSide > thirdSide && thirdSide + secondSide > firstSide && firstSide + thirdSide > secondSide;
    }
}
