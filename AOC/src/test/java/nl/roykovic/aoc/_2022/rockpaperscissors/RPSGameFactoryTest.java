package nl.roykovic.aoc._2022.rockpaperscissors;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPSGameFactoryTest {
    @Test
    void testOwnStrategyExampleScore() throws IOException {
        File input = new File("src/test/resources/2022/RockPaperScissorTestInput.txt");
        List<RPSGame> list = new RPSGameFactory().generateFromFile(input, false);

        int score = 0;

        for(RPSGame game : list){
            score += game.determineScore();
        }

        assertEquals(15, score);
    }

    @Test
    void testOwnStrategyActualScore() throws IOException {
        File input = new ClassPathResource("2022/RockPaperScissorInput.txt").getFile();
        List<RPSGame> list = new RPSGameFactory().generateFromFile(input, false);

        int score = 0;

        for(RPSGame game : list){
            score += game.determineScore();
        }

        assertEquals(11150, score);
    }

    @Test
    void testElfStrategyExampleScore() throws IOException {
        File input = new File("src/test/resources/2022/RockPaperScissorTestInput.txt");
        List<RPSGame> list = new RPSGameFactory().generateFromFile(input, true);

        int score = 0;

        for(RPSGame game : list){
            score += game.determineScore();
        }

        assertEquals(12, score);
    }

    @Test
    void testElfStrategyActualScore() throws IOException {
        File input = new ClassPathResource("2022/RockPaperScissorInput.txt").getFile();
        List<RPSGame> list = new RPSGameFactory().generateFromFile(input, true);

        int score = 0;

        for(RPSGame game : list){
            score += game.determineScore();
        }

        assertEquals(8295, score);
    }
}
