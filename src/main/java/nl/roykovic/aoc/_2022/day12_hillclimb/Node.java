package nl.roykovic.aoc._2022.day12_hillclimb;

import nl.roykovic.aoc.utils.Coord;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    private final Coord coord;
    private Integer distance = Integer.MAX_VALUE;
    private final int elevation;
    private final boolean isStart;
    private final boolean isEnd;

    private List<Node> shortestPath = new LinkedList<>();

    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(int x, int y) {
        this(new Coord(x, y));
    }

    public Node(Coord coord) {
        this.coord = coord;
        this.elevation =0;
        this.isStart = false;
        this.isEnd = false;
    }

    public Node(int x, int y, int elevation, boolean isStart, boolean isEnd) {
        this.coord = new Coord(x, y);
        this.elevation = elevation;
        this.isStart = isStart;
        this.isEnd = isEnd;
    }

    public int getX() {
        return coord.getX().intValue();
    }

    public int getY() {
        return coord.getY().intValue();
    }

    public Coord getCoord() {
        return coord;
    }

    public int getElevation() {
        return elevation;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
