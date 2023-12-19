package nl.roykovic.aoc._2023.day18_lagoon;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

public record CoordRange(Coord start, Coord end, Direction direction) {
    public Long getAmountOfCoords(){
        return Math.abs(end.getX() - start.getX() + end.getY() - start.getY());
    }
}
