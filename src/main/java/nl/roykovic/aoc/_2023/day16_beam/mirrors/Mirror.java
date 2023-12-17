package nl.roykovic.aoc._2023.day16_beam.mirrors;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.List;
import java.util.Map;

public class Mirror {
    private char mirrorChar;
    private Coord coord;

    public Mirror(char mirrorChar, Coord coord) {
        this.mirrorChar = mirrorChar;
        this.coord = coord;
    }

    public Map<Coord, Direction> move(Direction direction){
        return Map.of(coord.moveAndGet(direction), direction);
    }

    public char getMirrorChar() {
        return mirrorChar;
    }

    public void setMirrorChar(char mirrorChar) {
        this.mirrorChar = mirrorChar;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}
