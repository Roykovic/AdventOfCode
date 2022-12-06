package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.Elf;
import nl.roykovic.AOC.domain.ElfFactory;
import nl.roykovic.AOC.domain.RockPaperScissors.RPSGame;
import nl.roykovic.AOC.domain.RockPaperScissors.RPSGameFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class RPSGameFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("RockPaperScissorInput.txt").getFile();
        List<RPSGame> list = new RPSGameFactory().generateFromFile(input);

        int score = 0;

        for(RPSGame game : list){
            score += game.determineScore();
        }

        System.out.println(list.size());
        System.out.println(score);
    }
}
