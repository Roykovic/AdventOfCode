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
    public int[][] generateFromFile(File file, boolean brightness) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return generateFromStream(reader.lines(), brightness);
    }

    public int[][] generateFromStream(Stream<String> lines, boolean brightness){
        int[][] lights = new int[1000][1000];
        Pattern coordPattern = Pattern.compile("\\d+,\\d+");
        lines.forEach(line -> {
            Matcher m = coordPattern.matcher(line);
            List<Coord> coords = new ArrayList<>();

            while(m.find()){
                coords.add(new Coord(m.group()));
            }

            for(Coord coord: getCoordsInSquare(coords.get(0), coords.get(1))){
                if(line.contains("off")){
                    if(brightness && lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] > 0){
                        lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] -=1;
                    }
                    else{
                        lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] = 0;
                    }
                }
                if(line.contains("on")){
                    if(brightness){
                        lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] +=1;
                    }
                    else{
                        lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] = 1;
                    }
                }
                if(line.contains("toggle")){

                    if(brightness){
                        lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] +=2;
                    }
                    else{
                        int curValue = lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())];

                        lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] = curValue ==0?1:0;
                    }

                }
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
}
