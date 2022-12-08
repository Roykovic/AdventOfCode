package nl.roykovic.aoc.trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TreeFactory {
    public TreePatch generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        int x = lines.get(0).length();
        int y = lines.size();

        Tree[][] trees = new Tree[y][x];

        for(int yIndex = 0; yIndex < lines.size(); yIndex++){
            String line = lines.get(yIndex);
            for(int xIndex = 0; xIndex < line.length(); xIndex++){
                int height = line.charAt(xIndex) - '0';

                trees[yIndex][xIndex] = new Tree(height);
            }
        }
        return new TreePatch(trees);
    }
}
