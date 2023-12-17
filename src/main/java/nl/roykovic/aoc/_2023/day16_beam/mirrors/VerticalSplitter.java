package nl.roykovic.aoc._2023.day16_beam.mirrors;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.Map;

public class VerticalSplitter extends Mirror{

    // mirror with char |
    public VerticalSplitter(Coord coord) {
        super('|', coord);
    }
    @Override
    public Map<Coord, Direction> move(Direction direction) {

        if(direction == Direction.U || direction == Direction.D){
            return Map.of(this.getCoord().moveAndGet(direction),direction);
        }

        return Map.of(this.getCoord().moveAndGet(Direction.U), Direction.U, this.getCoord().moveAndGet(Direction.D), Direction.D);
    }
}
