package nl.roykovic.aoc._2022.day8_trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TreeFactory {
    public TreePatch generateFromFile(List<String> lines){
        int x = lines.get(0).length();
        int y = lines.size();

        Tree[][] trees = new Tree[y][x];

        for(int yIndex = 0; yIndex < lines.size(); yIndex++){
            String line = lines.get(yIndex);
            for(int xIndex = 0; xIndex < line.length(); xIndex++){
                int height = line.charAt(xIndex) - '0';

                trees[yIndex][xIndex] = new Tree(height, xIndex, yIndex);
            }
        }
        return new TreePatch(trees);
    }
}
