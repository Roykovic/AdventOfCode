package nl.roykovic.aoc._2023.day2_cubegame;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CubeGameFactoryTest {
    @Test
    void testExampleCubeGame() throws IOException {
        File input = new File("src/test/resources/2023/CubeGameTestInput.txt");
        var games = new CubeGameFactory().generateFromFile(input);

        int red = 12;
        int green = 13;
        int blue = 14;

        int gameSum = 0;

        for(int i = 0; i < games.size(); i++){
            boolean possible = true;
            for(Map<String, Integer> currGame : games.get(i)){
                if(currGame.getOrDefault("red", 0) > red || currGame.getOrDefault("green", 0) > green || currGame.getOrDefault("blue", 0) > blue){
                    possible = false;
                }
            }
            if(possible){
                gameSum += (i+1);
            }
        }

        assertEquals(8, gameSum);
    }

    @Test
    void testActualCubeGame() throws IOException {
        File input = new ClassPathResource("2023/CubeGameInput.txt").getFile();
        var games = new CubeGameFactory().generateFromFile(input);

        int red = 12;
        int green = 13;
        int blue = 14;

        int gameSum = 0;

        for(int i = 0; i < games.size(); i++){
            boolean possible = true;
            for(Map<String, Integer> currGame : games.get(i)){
                if(currGame.getOrDefault("red", 0) > red || currGame.getOrDefault("green", 0) > green || currGame.getOrDefault("blue", 0) > blue){
                    possible = false;
                }
            }
            if(possible){
                gameSum += (i+1);
            }
        }

        assertEquals(2563, gameSum);
    }

    @Test
    void testExampleCubeGamePower() throws IOException {
        File input = new File("src/test/resources/2023/CubeGameTestInput.txt");
        var games = new CubeGameFactory().generateFromFile(input);

        var powerSum = games.stream().map(it -> it.stream()
                .flatMap(m ->  m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::max)))
                .map(it -> it.values().stream().mapToInt(num -> num).reduce((i, j) -> i * j).orElseThrow())
                .mapToInt(it -> it)
                .sum();



        assertEquals(2286, powerSum);
    }

    @Test
    void testActualCubeGamePower() throws IOException {
        File input = new ClassPathResource("2023/CubeGameInput.txt").getFile();
        var games = new CubeGameFactory().generateFromFile(input);

        var powerSum = games.stream().map(it -> it.stream()
                        .flatMap(m ->  m.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::max)))
                .map(it -> it.values().stream().mapToInt(num -> num).reduce((i, j) -> i * j).orElseThrow())
                .mapToInt(it -> it)
                .sum();



        assertEquals(70768, powerSum);
    }
}
