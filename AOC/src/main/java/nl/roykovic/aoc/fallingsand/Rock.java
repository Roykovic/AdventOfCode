package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.utils.Coord;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rock rock = (Rock) o;
        return Objects.equals(coord, rock.coord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coord);
    }
}
