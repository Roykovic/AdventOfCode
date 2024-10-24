package nl.roykovic.aoc._2015.day21_bossfight;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BossfightFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "BossfightTestInput.txt,true,-1",
            "BossfightInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);
        var factory = new BossfightFactory();
        var boss = factory.generateBoss(input);

        Player you;

        if(test){
            you = new Player(8, 5, 5);
        }
        else{
            you = new Player(100,0,0);
        }

        assertEquals(factory.calculateWinner(you, boss), you);
    }

    @ParameterizedTest
    @CsvSource({
            "BossfightInput.txt,false,-1",
    })
    public void testMinimumModifier(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);
        var factory = new BossfightFactory();
        var boss = factory.generateBoss(input);

        Player you = new Player(100,4,6);

        //5,4 or 4,5

        Player winner = factory.calculateWinner(you, boss);

        //>116 <193 not 119
        assertEquals(winner, you);
    }
}