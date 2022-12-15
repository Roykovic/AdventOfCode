package nl.roykovic.aoc._2015.present;

import nl.roykovic.aoc.utils.Coord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HouseFactory {
    public List<Coord> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        return this.generateFromString(reader.lines().findFirst().orElseThrow(IllegalArgumentException::new));

    }

    public List<Coord> generateFromString(String input){

        char[] instructions = input.toCharArray();
        List<Coord> houseCoords = new ArrayList<>(List.of(new Coord(0L, 0L)));
        for(int i = 0; i < instructions.length; i++){
            houseCoords.add(instructionToCoord(houseCoords.get(i), instructions[i]));
        }

        return houseCoords.stream().distinct().collect(Collectors.toList());
    }

    private Coord instructionToCoord(Coord previousCoord, char instruction){
        switch(instruction){
            case '^' -> {
                return new Coord(previousCoord.getX(), previousCoord.getY() -1);
            }
            case '>' -> {
                return new Coord(previousCoord.getX() +1, previousCoord.getY());
            }
            case 'v' -> {
                return new Coord(previousCoord.getX(), previousCoord.getY() +1);
            }
            case '<' -> {
                return new Coord(previousCoord.getX() -1, previousCoord.getY());
            }
            default -> throw new IllegalArgumentException(previousCoord + " " + instruction);
        }
    }
}
