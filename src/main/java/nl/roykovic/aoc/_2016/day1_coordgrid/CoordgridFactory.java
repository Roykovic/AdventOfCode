package nl.roykovic.aoc._2016.day1_coordgrid;

import nl.roykovic.aoc._2022.day16_volcano.Volcano;
import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class CoordgridFactory {
    public int generate(String input, boolean stopAtSameLocation) {
        var directions = input.split(" ");

        List<Coord> visited = new ArrayList<>();

        Coord start = new Coord(0,0);

        visited.add(new Coord(start));
        Direction startDir = Direction.U;

        for(String d : directions){
            Direction direction = Direction.valueOf(String.valueOf(d.charAt(0)));
            d = d.replace(",", "");
            int times = Integer.parseInt(d.substring(1));

            Direction relativeDir = Direction.getFromViewingDirection(startDir, direction);

            for(int i = 0; i < times; i++){
                start.move(relativeDir);
                if(visited.contains(start) && stopAtSameLocation){
                    return Math.toIntExact(start.manhattanDistance(new Coord(0, 0)));
                }
                visited.add(new Coord(start));
            }
            startDir = relativeDir;
        }

        return Math.toIntExact(start.manhattanDistance(new Coord(0, 0)));
    }
}
