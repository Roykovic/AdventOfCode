package nl.roykovic.aoc._2024.day4_wordsearch;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class WordSearchFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "WordSearchTestInput.txt,true,18",
            "WordSearchInput.txt,false,2639",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2024, filename, test);

        WordSearchFactory wordSearchFactory = new WordSearchFactory();

        var wordSearch = wordSearchFactory.generate(input);
        var output = wordSearchFactory.countAllMatches(wordSearch);


        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "WordSearchTestInput.txt,true,9",
            "WordSearchInput.txt,false,2005",
    })
    public void testP2(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2024, filename, test);

        WordSearchFactory wordSearchFactory = new WordSearchFactory();

        var wordSearch = wordSearchFactory.generate(input);
        var output = wordSearchFactory.countOverlappingDiagonalMatches(wordSearch);


        assertEquals(expected, output);
    }


    @Test
    public  void openInIntelliJ() {
        String fileName = "src/main/java/nl/roykovic/aoc/_2015/day1_apartment/ApartmentFactory.java";
        try {
            Process process = new ProcessBuilder("C:\\Users\\roy_i\\AppData\\Local\\JetBrains\\Toolbox\\scripts\\idea.cmd", fileName)
                    .start();
            process.waitFor(); // Wait for the command to complete
        } catch (IOException | InterruptedException e) {
            System.out.println("Error opening file in IntelliJ: " + e.getMessage());
        }
    }
}
