package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.utils.Coord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FallingSandFactory {

    private int highestY;
    public Cave generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        HashMap<Coord, IParticle> particles = new HashMap<Coord, IParticle>(
                reader.lines().distinct()
                        .map(it -> it.split(" -> "))
                        .flatMap(coordinates -> connectRocks(
                                Arrays.stream(coordinates)
                                        .map(coordinate -> new Rock(new Coord(coordinate))))).distinct().collect(Collectors.toMap(IParticle::getCoord, Function.identity())));

        return new Cave(particles, highestY);
    }

    private Stream<Rock> connectRocks(Stream<Rock> rockStream){
        Set<Rock> returnList = new HashSet<>();
        List<Rock> rocks = rockStream.toList();

        for(int i = 1; i < rocks.size(); i++){
            Rock first = rocks.get(i -1);
            Rock second = rocks.get(i);

            int smallestX = Math.min(first.getCoord().getX(), second.getCoord().getX());
            int biggestX = Math.max(first.getCoord().getX(), second.getCoord().getX());
            int smallestY = Math.min(first.getCoord().getY(), second.getCoord().getY());
            int biggestY = Math.max(first.getCoord().getY(), second.getCoord().getY());

            if(biggestY > highestY){
                highestY = biggestY;
            }

            for(int x = smallestX +1; x < biggestX; x++){
                Coord coord = new Coord(x, smallestY);
                returnList.add(new Rock(coord));
            }
            for(int y = smallestY +1; y < biggestY; y++){
                Coord coord = new Coord(smallestX, y);
                returnList.add(new Rock(coord));
            }
        }

        returnList.addAll(rocks);
        return returnList.stream();
    }
}
