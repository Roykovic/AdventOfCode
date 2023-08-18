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
    public Boolean[][] generateFromFile(File file) throws FileNotFoundException {



        BufferedReader reader = new BufferedReader(new FileReader(file));
       return generateFromStream(reader.lines());
    }

    public Boolean[][] generateFromStream(Stream<String> lines){
        Boolean[][] lights = new Boolean[1000][1000];
        Pattern coordPattern = Pattern.compile("\\d+,\\d+");
        lines.forEach(line -> {
            Matcher m = coordPattern.matcher(line);
            List<Coord> coords = new ArrayList<>();

            while(m.find()){
                coords.add(new Coord(m.group()));
            }

            for(Coord coord: getCoordsInSquare(coords.get(0), coords.get(1))){

                if(lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] == null){
                    lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] = Boolean.FALSE;
                }

                if(line.contains("off")){
                    lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] = false;
                }
                if(line.contains("on")){
                    lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] = true;
                }
                if(line.contains("toggle")){
                    lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())] = !lights[Math.toIntExact(coord.getX())][Math.toIntExact(coord.getY())];
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
