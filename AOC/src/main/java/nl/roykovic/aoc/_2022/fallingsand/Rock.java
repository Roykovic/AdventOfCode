package nl.roykovic.aoc._2022.fallingsand;

import nl.roykovic.aoc.utils.Coord;

import java.util.Objects;

public record Rock(Coord coord) implements IParticle {

    @Override
    public String toString() {
        return "(" + coord.getX() + ", " + coord.getY() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rock rock = (Rock) o;
        return Objects.equals(coord, rock.coord);
    }

}
