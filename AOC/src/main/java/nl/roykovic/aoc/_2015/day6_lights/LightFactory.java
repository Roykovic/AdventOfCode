package nl.roykovic.aoc._2015.day6_lights;

import nl.roykovic.aoc._2015.day5_naughtystrings.NaughtyOrNiceFactory;
import nl.roykovic.aoc.utils.Coord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LightFactory {

    private final int[][] lights = new int[1000][1000];

    public int[][] generateFromFile(File file, boolean brightness) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return generateFromStream(reader.lines(), brightness);
    }

    public int[][] generateFromStream(Stream<String> lines, boolean brightness){

        Pattern coordPattern = Pattern.compile("\\d+,\\d+");
        lines.forEach(line -> {
            Matcher m = coordPattern.matcher(line);
            List<Coord> coords = new ArrayList<>();

            while(m.find()){
                coords.add(new Coord(m.group()));
            }

            for(Coord coord: getCoordsInSquare(coords.get(0), coords.get(1))){
                switchLight(line, coord,brightness);
            };
        });


        return lights;
    }

    private List<Coord> getCoordsInSquare(Coord corner1, Coord corner2){

        List<Coord> coords = new ArrayList<>();

        for(long x = corner1.getX(); x <= corner2.getX(); x++){
            for(long y = corner1.getY(); y <= corner2.getY(); y++){
                coords.add(new Coord(x,y));
            }
        }
        return coords;
    }

    private void switchLight(String instruction, Coord coord, boolean brightness) {

        LightInstruction lightInstruction = LightInstruction.getIntructionFromString(instruction);
        int curValue = lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())];
        if (brightness) {
             curValue += lightInstruction.getValue();

        } else if(curValue != lightInstruction.getSwitchedValue()) {
            switch (lightInstruction){
                case OFF -> curValue = 0;
                case ON -> curValue = 1;
                case TOGGLE -> curValue ^= 1;
            }
        }
        lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] = Math.max(0,curValue);
    }
}
