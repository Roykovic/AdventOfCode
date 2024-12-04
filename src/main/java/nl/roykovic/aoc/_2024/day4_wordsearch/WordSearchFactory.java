package nl.roykovic.aoc._2024.day4_wordsearch;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nl.roykovic.aoc.utils.Direction.*;
import static nl.roykovic.aoc.utils.Utils.rotateGridClockwise;

public class WordSearchFactory {
    public char[][] generate(Stream<String> input) {
        return input.map(String::toCharArray).toArray(char[][]::new);
    }

    public int countOverlappingDiagonalMatches(char[][] wordSearch){
        int count = 0;

        List<List<Coord>> diagonalMatches = new ArrayList<>();

        for(int x = 0; x < wordSearch[0].length; x++){
            for(int y = 0; y< wordSearch.length; y++){

                if(wordSearch[y][x] == 'M'){
                    Coord curCoord = new Coord(x,y);

                    List<Coord> neigbhours = curCoord.getNeighboursDiagonal();

                    for(Coord c : neigbhours){
                        Direction d = Direction.getByCoords(c, curCoord);
                        if(hasMatch(curCoord, d, wordSearch, new char[]{'M', 'A', 'S'})){
                            diagonalMatches.add(List.of(curCoord, curCoord.moveAndGet(d), curCoord.moveAndGet(d).moveAndGet(d)));
                        }
                    }

                }

            }
        }

        return (int) diagonalMatches.stream()
                .map(it -> it.get(1))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .filter(it -> it == 2)
                .count();
    }

    public int countAllMatches(char[][] wordSearch){
        int matches = 0;

        matches += countHorizontalMatches(wordSearch);

        wordSearch = rotateGridClockwise(wordSearch);
        matches += countHorizontalMatches(wordSearch);

        wordSearch = rotateGridClockwise(wordSearch);
        matches += countHorizontalMatches(wordSearch);

        wordSearch = rotateGridClockwise(wordSearch);
        matches += countHorizontalMatches(wordSearch);

        matches += countDiagonalMatches(wordSearch);
        return matches;
    }

    private int countHorizontalMatches(char[][] wordSearch){
        int count = 0;

        for(char[] wordSearchLine : wordSearch) {
            String line = new String(wordSearchLine);
            count += StringUtils.countMatches(line, "XMAS");
        }
        return count;
    }

    private int countDiagonalMatches(char[][] wordSearch){
        int count = 0;

        for(int x = 0; x < wordSearch[0].length; x++){
            for(int y = 0; y< wordSearch.length; y++){

                if(wordSearch[y][x] == 'X'){
                    Coord curCoord = new Coord(x,y);

                    List<Coord> neigbhours = curCoord.getNeighboursDiagonal();

                    for(Coord c : neigbhours){
                        if(hasMatch(curCoord, Direction.getByCoords(c, curCoord), wordSearch, new char[]{'X', 'M', 'A', 'S'})){
                            count++;
                        }
                    }

                }

            }
        }

        return count;
    }

    private boolean hasMatch(Coord start, Direction d, char[][] wordSearch, char[] result){
        Coord curCoord = new Coord(start);
        for (char c : result) {
            if (curCoord.getX() < 0 || curCoord.getY() < 0) {
                return false;
            }
            if (curCoord.getX() >= wordSearch[0].length || curCoord.getY() >= wordSearch.length) {
                return false;
            }
            if (!(wordSearch[curCoord.getY().intValue()][curCoord.getX().intValue()] == c)) {
                return false;
            }
            curCoord.move(d);
        }
        return true;
    }
}
