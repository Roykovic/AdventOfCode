package nl.roykovic.aoc._2023.day2_cubegame;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CubeGameFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "CubeGameTestInput.txt,true,8",
            "CubeGameInput.txt,false,2563"
    })
    void testCubeGame(String filename, boolean test, int expected) throws IOException {
        var input = FileReaderService.streamLinesFromFile(2023,filename, test);
        var games = new CubeGameFactory().generate(input);

        int red = 12;
        int green = 13;
        int blue = 14;

        var possibleList = games.stream()
                .map(game -> game.stream()
                        .map(set -> !(set.getOrDefault("red", 0) > red
                                || set.getOrDefault("green", 0) > green
                                || set.getOrDefault("blue", 0) > blue))
                        .toList())
                .map(it -> !it.contains(false))
                .toList();

        gameSum = IntStream.range(0, possibleList.size())
                .map(i -> possibleList.get(i)? i+1:0)
                .sum();

        assertEquals(expected, gameSum);
    }

    @ParameterizedTest
    @CsvSource({
            "CubeGameTestInput.txt,true,2286",
            "CubeGameInput.txt,false,70768"
    })
    void testCubeGamePower(String filename, boolean test, int expected) throws IOException {
        var input = FileReaderService.streamLinesFromFile(2023, filename, test);
        var games = new CubeGameFactory().generate(input);

        var powerSum = games.stream().map(it -> it.stream()
                .flatMap(m ->  m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::max)))
                .map(it -> it.values().stream().mapToInt(num -> num).reduce((i, j) -> i * j).orElseThrow())
                .mapToInt(it -> it)
                .sum();

        assertEquals(expected, powerSum);
    }
}
