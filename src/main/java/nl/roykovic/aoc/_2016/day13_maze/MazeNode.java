package nl.roykovic.aoc._2016.day13_maze;

import nl.roykovic.aoc._2022.day12_hillclimb.Node;
import nl.roykovic.aoc.utils.Coord;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MazeNode extends Node {
    private int favoriteNumber;

    public MazeNode(Coord coord, int favoriteNumber) {
        super(coord);
        this.favoriteNumber = favoriteNumber;
    }

    @Override
    public Map<Node, Integer> getAdjacentNodes() {
        return this.getCoord()
                .getNeighboursStraight().stream()
                .filter(this::isNode)
                .map(it -> new MazeNode(it, this.favoriteNumber))
                .collect((Collectors.toMap(Function.identity(), it -> 1)));

    }

    private boolean isNode(Coord c){
        long x = c.getX();
        long y = c.getY();

        if(x < 0 || y < 0) {
            return  false;
        }

        long calc = x*x + 3*x + 2*x*y + y + y*y;

        calc += favoriteNumber;

        String binary = Long.toBinaryString(calc);

        int ones = StringUtils.countMatches(binary, "1");

        return ones % 2 == 0;
    }

    @Override
    public String toString() {
        return "Mazenode (" + getX() + ", " + getY() +")";
    }
}
