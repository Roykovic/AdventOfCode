package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.utils.Coord;

public class Rock implements IParticle {
    private Coord coord;

    public Rock(Coord coord) {
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        return "(" + coord.getX() + ", " + coord.getY() +  ")";
    }
}
