package nl.roykovic.aoc._2023.day16_beam.mirrors;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.Map;

public class HorizontalSplitter extends Mirror{

    // mirror with char -
    public HorizontalSplitter(Coord coord) {
        super('-', coord);
    }
    @Override
    public Map<Coord, Direction> move(Direction direction) {

        if(direction == Direction.L || direction == Direction.R){
            return Map.of(this.getCoord().moveAndGet(direction),direction);
        }

        return Map.of(this.getCoord().moveAndGet(Direction.L), Direction.L, this.getCoord().moveAndGet(Direction.R), Direction.R);
    }
}
