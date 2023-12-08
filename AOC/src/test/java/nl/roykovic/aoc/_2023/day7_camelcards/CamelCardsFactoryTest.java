package nl.roykovic.aoc._2023.day7_camelcards;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CamelCardsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "CamelCardsTestInput.txt,true,6440",
            "CamelCardsInput.txt,false,252656917"
    })
    public void testWinnings(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new CamelCardsFactory().generate(input, false);
        int sum = 0;
        for(int i = 0; i <output.size(); i++){
            sum += (output.get(i).getBid() * (i+1));
        }
        assertEquals(expected, sum);
    }

    @ParameterizedTest
    @CsvSource({
            "CamelCardsTestInput.txt,true,5905",
            "CamelCardsInput.txt,false,253499763"
    })
    public void testWinningsWithJoker(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var output = new CamelCardsFactory().generate(input, true);
        int sum = 0;
        for(int i = 0; i <output.size(); i++){
            sum += (output.get(i).getBid() * (i+1));
        }
        assertEquals(expected, sum);
    }
}
