package nl.roykovic.aoc._2023.day16_beam.mirrors;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.Map;

public class SlantedLeftMirror extends Mirror{

    // mirror with char \
    public SlantedLeftMirror(Coord coord) {
        super('\\', coord);
    }
    @Override
    public Map<Coord, Direction> move(Direction direction) {

        Direction directionFrom = Direction.OPPOSITE.get(direction);

        Coord newCoord = new Coord(this.getCoord().getX() - directionFrom.getAdditionalY(), this.getCoord().getY() - directionFrom.getAdditionalX());

        int newDX = (int) (newCoord.getX() - this.getCoord().getX());
        int newDY = (int) (newCoord.getY() - this.getCoord().getY());

        Direction dir = Direction.getByCoords(newDX,newDY);

        return Map.of(newCoord, dir);
    }
}
