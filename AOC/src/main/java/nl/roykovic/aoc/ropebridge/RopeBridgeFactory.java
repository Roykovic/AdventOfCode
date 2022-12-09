package nl.roykovic.aoc.ropebridge;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RopeBridgeFactory {
    public List<Map.Entry<Integer, Integer>> generateFromFile(File file, int amountOfTails) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        Head head = new Head(0,0);

        List<Tail> tails = new ArrayList<>();
        for(int tailIndex = 0; tailIndex < amountOfTails; tailIndex++) {
           tails.add(new Tail(0, 0));
        }

        for(String line: lines){
            String[] parts =line.split(" ");
            Direction direction = Direction.valueOf(parts[0]);
            int amount = NumberUtils.createInteger(parts[1]);

            for(int i = 0; i< amount; i++){
                head.move(direction);
                for(int j = 0; j < amountOfTails; j++){
                    RopeEnd endToFollow = j==0?head:tails.get(j-1); //the first tail should follow the head, the rest should follow its predecessor

                    tails.get(j).moveToRopeEnd(endToFollow); //move current tail to predecessors location
                }
            }
        }
        return tails.get(tails.size() -1).path; //return path from last tail
    }
}
