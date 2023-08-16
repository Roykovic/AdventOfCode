package nl.roykovic.aoc._2022.monkeymap;

import nl.roykovic.aoc._2022.trees.Tree;
import nl.roykovic.aoc._2022.trees.TreePatch;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;

public class MonkeyMapFactory {
    public MonkeyMap generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        String[] instructions = lines.get(lines.size() -1).split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

        lines = lines.subList(0, lines.size() -2);

        int x = lines.stream().max(Comparator.comparingInt(String::length)).get().length();
        int y = lines.size();

        char[][] map = new char[y][x];

        for(int yIndex = 0; yIndex < lines.size(); yIndex++){
            String line = lines.get(yIndex);
            for(int xIndex = 0; xIndex < line.length(); xIndex++){
                char c = line.charAt(xIndex);

                map[yIndex][xIndex] = c;
            }
        }
        return new MonkeyMap(map, instructions);
    }
}
