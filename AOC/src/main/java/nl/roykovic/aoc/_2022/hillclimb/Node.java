package nl.roykovic.aoc._2022.hillclimb;

import java.util.*;

public class Node {
    private final int x;
    private final int y;

    private Integer distance = Integer.MAX_VALUE;
    private final int elevation;
    private final boolean isStart;
    private final boolean isEnd;

    private List<Node> shortestPath = new LinkedList<>();

    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(int x, int y, int elevation, boolean isStart, boolean isEnd) {
        this.x = x;
        this.y = y;
        this.elevation = elevation;
        this.isStart = isStart;
        this.isEnd = isEnd;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
