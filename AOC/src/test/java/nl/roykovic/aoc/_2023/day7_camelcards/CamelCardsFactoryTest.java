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
        var hans = new CamelCardsFactory().generate(input);
        int sum = 0;
        for(int i = 0; i <hans.size(); i++){
            sum += (hans.get(i).getBid() * (i+1));
        }
        assertEquals(expected, sum);
    }
}
