package nl.roykovic.aoc;

import nl.roykovic.aoc.domain.rockpaperscissors.RPSGame;
import nl.roykovic.aoc.domain.rockpaperscissors.RPSGameFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RPSGameFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("RockPaperScissorInput.txt").getFile();
        List<RPSGame> list = new RPSGameFactory().generateFromFile(input, false);

        int score = 0;

        for(RPSGame game : list){
            score += game.determineScore();
        }

        System.out.println(list.size());
        System.out.println(score);
    }

    @Test
    void testFactory2() throws IOException {
        File input = new ClassPathResource("RockPaperScissorInput.txt").getFile();
        List<RPSGame> list = new RPSGameFactory().generateFromFile(input, true);

        int score = 0;

        for(RPSGame game : list){
            score += game.determineScore();
        }

        System.out.println(list.size());
        System.out.println(score);
    }
}
