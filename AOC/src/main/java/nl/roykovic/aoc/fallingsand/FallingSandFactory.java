package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.rucksack.Rucksack;
import nl.roykovic.aoc.utils.Coord;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FallingSandFactory {
    public List<IParticle> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().distinct().toList();
        List<IParticle> rocks = new ArrayList<>();

        for(String line: lines){
            String[] coordinates = line.split(" -> ");
            List<Rock> rockList = new ArrayList<>();
            for(String coordinate : coordinates){
                rockList.add(new Rock(new Coord(coordinate)));
            }
            rocks.addAll(connectRocks(rockList));
        }
        return rocks;
    }

    private List<Rock> connectRocks(List<Rock> rocks){
        List<Rock> returnList = new ArrayList<>();
        for(int i = 1; i < rocks.size(); i++){
            Rock first = rocks.get(i -1);
            Rock second = rocks.get(i);

            int smallestX = Math.min(first.getCoord().getX(), second.getCoord().getX());
            int biggestX = Math.max(first.getCoord().getX(), second.getCoord().getX());
            int smallestY = Math.min(first.getCoord().getY(), second.getCoord().getY());
            int biggestY = Math.max(first.getCoord().getY(), second.getCoord().getY());

            for(int x = smallestX +1; x < biggestX; x++){
                returnList.add(new Rock(new Coord(x, smallestY)));
            }
            for(int y = smallestY +1; y < biggestY; y++){
                returnList.add(new Rock(new Coord(smallestX, y)));
            }
        }

        returnList.addAll(rocks);
        return returnList;
    }
}
