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
        List<TrenchCoord> trenches = new ArrayList<>();

        for(String[] arr : input.map(it -> it.split(" ")).toList()){
            List<TrenchCoord> coords = new ArrayList<>();
            for(int i =0; i< Integer.parseInt(arr[1]); i++){
                start.move(Direction.valueOf(arr[0]));
                coords.add(new TrenchCoord(new Coord(start), Color.decode(arr[2].replace("(", "").replace(")", ""))));
            }
            trenches.addAll(coords);
        }

        //Picks theorem again, we can calculate area, we have boundary points. We want to know Interior points + boundary points:
//        drawGrid(trenches);
        var area = Utils.shoelaceArea(trenches.stream().map(TrenchCoord::coord).toList());
        var interiorPoints = Math.abs(((double) trenches.size() /2) -1 -area);

        return (int) (trenches.size()+interiorPoints);

    }

    private void drawGrid(List<TrenchCoord> coords){
        long maxX = coords.stream().mapToLong(it -> it.coord.getX()).max().orElseThrow();
        long maxY = coords.stream().mapToLong(it -> it.coord.getY()).max().orElseThrow();

        for(int y = 0; y<= maxY; y++){
            for(int x = 0; x <= maxX; x++){
                int finalX = x;
                int finalY = y;
                if(coords.stream().anyMatch(it -> Objects.equals(it.coord, new Coord(finalX, finalY)))){
                    System.out.printf("#");
                }
                else {
                    System.out.printf(".");
                }
            }
            System.out.println();
        }
    }

    private record TrenchCoord(Coord coord, Color color) {
    }
}
