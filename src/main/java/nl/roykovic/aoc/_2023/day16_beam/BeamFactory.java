package nl.roykovic.aoc._2023.day16_beam;

import nl.roykovic.aoc._2023.day16_beam.mirrors.Mirror;
import nl.roykovic.aoc._2023.day16_beam.mirrors.MirrorFactory;
import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import javax.print.attribute.standard.Destination;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class BeamFactory {

    char[][] inputGrid;
    char[][] outputGrid;
    public int generate(String input) {
        inputGrid = Arrays
                .stream(input.split("\\r\\n+"))
                .map(String::toCharArray).toArray(char[][]::new);

        List<Map.Entry<Coord, Direction>> visitedCoords = new ArrayList<>();

        Coord oldCoord = new Coord(-1,0);

        List<Map.Entry<Coord, Direction>> coordsToCheck = List.of(Map.entry(oldCoord.moveAndGet(Direction.R), Direction.R));
        List<Map.Entry<Coord, Direction>> currentCoords;
        while(!coordsToCheck.isEmpty()){
            currentCoords = new ArrayList<>();
            for(Map.Entry<Coord, Direction> e : coordsToCheck){
                if(e.getKey().equals(new Coord(36,2))){
                    System.out.println();
                }
                if(visitedCoords.contains(e)){
                    continue;
                }

                visitedCoords.add(e);

                Direction currentDir = e.getValue();
                Coord newCoord = e.getKey();

                Mirror currentMirror = MirrorFactory.getMirrorForChar(
                        inputGrid[Math.toIntExact(newCoord.getY())]
                                [Math.toIntExact(newCoord.getX())], newCoord);

                currentCoords.addAll(currentMirror.move(currentDir).entrySet().stream()
                        .filter(it -> {
                            Coord coord = it.getKey();
                            return (coord.getX() >= 0 && coord.getX() < inputGrid[0].length) &&
                                    (coord.getY() >= 0 && coord.getY() < inputGrid.length);
                        }).toList());
            }
            coordsToCheck = currentCoords;
        }

        drawGrid(visitedCoords);
        return visitedCoords.stream().map(Map.Entry::getKey).distinct().toList().size();
    }

    public void drawGrid(List<Map.Entry<Coord, Direction>> lissie){
        for(int y = 0; y< inputGrid.length; y++){
            for(int x = 0; x< inputGrid[y].length; x++){
                int finalX = x;
                int finalY = y;
                var foundCoords = lissie.stream().filter(it -> it.getKey().getX() == finalX && it.getKey().getY() == finalY).toList();

                if(foundCoords.size() > 0 && inputGrid[y][x] == '.'){
                    if(foundCoords.size() > 1){
                        System.out.print(foundCoords.size());
                    }
                    else{
                        System.out.print(getCharFromDir(foundCoords.get(0).getValue()));
                    }
                }
                else {
                    System.out.print(inputGrid[y][x]);;
                }

            }
            System.out.println();
        }
    }

    private char getCharFromDir(Direction d){
        switch (d){
            case R -> {
                return '>';
            }
            case L -> {
                return '<';
            }
            case U -> {
                return '^';
            }
            case D -> {
                return 'v';
            }
        }
        return 0;
    }
}
