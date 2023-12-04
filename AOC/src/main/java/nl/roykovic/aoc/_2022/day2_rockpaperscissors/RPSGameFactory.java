package nl.roykovic.aoc._2022.day2_rockpaperscissors;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

public class RPSGameFactory {
    public Stream<RPSGame> generateFromFile(Stream<String> input, boolean elvesSolution) throws FileNotFoundException {
        return input
                .map(StringUtils::deleteWhitespace)
                .map(String::toCharArray)
                .map(moves -> elvesSolution? new RPSGame(moves[0], moves[1], true):  new RPSGame(moves[0], moves[1]));
    }
}
