package nl.roykovic.aoc._2024.day4_wordsearch;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
}
