package nl.roykovic.aoc._2016.day13_maze;

import nl.roykovic.aoc.utils.BreadthFirstSearch;
import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.DijkstraService;

import java.util.stream.Stream;
public class MazeFactory {

    public int generate(String input, int endX, int endY) {
        int favoriteNumber = Integer.parseInt(input);

        return BreadthFirstSearch.search(new MazeNode(new Coord(1,1),favoriteNumber), new MazeNode(new Coord(endX, endY), favoriteNumber)).size();

    }

    public int generateMaxDepth(String input, int maxDepth) {
        int favoriteNumber = Integer.parseInt(input);

        return DijkstraService.calculateNodesInReach(new MazeNode(new Coord(1,1),favoriteNumber), maxDepth).size();

    }
}
