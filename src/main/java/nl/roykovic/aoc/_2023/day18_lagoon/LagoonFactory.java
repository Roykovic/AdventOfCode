package nl.roykovic.aoc._2023.day18_lagoon;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import nl.roykovic.aoc.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
public class LagoonFactory {
    public int generate(Stream<String> input) {

        Coord start = new Coord(0,0);
        List<Coord> trenches = new ArrayList<>();

        for(String[] arr : input.map(it -> it.split(" ")).toList()){
            List<Coord> coords = new ArrayList<>();
            for(int i =0; i< Integer.parseInt(arr[1]); i++){
                start.move(Direction.valueOf(arr[0]));
                coords.add(new Coord(start));
            }
            trenches.addAll(coords);
        }

        //Picks theorem again, we can calculate area, we have boundary points. We want to know Interior points + boundary points:
//        drawGrid(trenches);
        var area = Utils.shoelaceArea(trenches);
        var interiorPoints = Math.abs(((double) trenches.size() /2) -1 -area);

        return (int) (trenches.size()+interiorPoints);

    }
}
