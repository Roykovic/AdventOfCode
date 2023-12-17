package nl.roykovic.aoc._2023.day16_beam;

import nl.roykovic.aoc._2023.day16_beam.mirrors.Mirror;
import nl.roykovic.aoc._2023.day16_beam.mirrors.MirrorFactory;
import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

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

        Map<Coord, Direction> coordsToCheck = Map.of(oldCoord.moveAndGet(Direction.R), Direction.R);
        Map<Coord, Direction> currentCoords;
        while(!coordsToCheck.isEmpty()){
            currentCoords = new HashMap<>();
            for(Map.Entry<Coord, Direction> e : coordsToCheck.entrySet()){
                if(visitedCoords.contains(e)){
                    continue;
                }

                visitedCoords.add(e);

                Direction currentDir = e.getValue();
                Coord newCoord = e.getKey();

                Mirror currentMirror = MirrorFactory.getMirrorForChar(
                        inputGrid[Math.toIntExact(newCoord.getY())]
                                [Math.toIntExact(newCoord.getX())], newCoord);

                currentCoords.putAll(currentMirror.move(currentDir).entrySet().stream()
                        .filter(it -> {
                            Coord coord = it.getKey();
                            return (coord.getX() >= 0 && coord.getX() < inputGrid[0].length) &&
                                    (coord.getY() >= 0 && coord.getY() < inputGrid.length);
                        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
            }
            coordsToCheck = currentCoords;
        }
        return visitedCoords.stream().map(Map.Entry::getKey).distinct().toList().size();
    }
}
