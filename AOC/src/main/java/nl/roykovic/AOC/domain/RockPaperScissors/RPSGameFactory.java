package nl.roykovic.AOC.domain.RockPaperScissors;

import nl.roykovic.AOC.domain.Elf;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RPSGameFactory {
    public List<RPSGame> generateFromFile(File file, boolean elvesSolution) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        List<RPSGame> gameList = new ArrayList<>();

        for(String line: lines){
            char[] moves = StringUtils.deleteWhitespace(line).toCharArray();

            RPSGame game;
            if(elvesSolution){
                game = new RPSGame(moves[0], moves[1], elvesSolution);
            }
            else {
                game = new RPSGame(moves[0], moves[1]);
            }
            gameList.add(game);
        }
        return gameList;
    }
}
