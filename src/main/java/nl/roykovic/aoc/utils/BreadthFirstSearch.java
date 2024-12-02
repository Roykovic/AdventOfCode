package nl.roykovic.aoc.utils;

import nl.roykovic.aoc._2022.day12_hillclimb.Node;

import java.util.*;

public class BreadthFirstSearch {

    public static List<Node> search(Node startNode, Node endNode) {

        Map<Node, Node> parents = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        parents.put(startNode, null);
        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.equals(endNode)) {
                List<Node> path = new ArrayList<>();

                while(current != null){
                    path.add(current);
                    current = parents.get(current);
                }

                return path;

            } else {
                for (Node neighbour : current.getAdjacentNodes().keySet()) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        parents.put(neighbour, current);
                        queue.offer(neighbour);
                    }
                }
            }
        }
        return null;
    }

    public static List<Node> searchCircular(Node startNode, Node endNode) {

        Map<Node, Node> parents = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        parents.put(startNode, null);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.equals(endNode)) {
                List<Node> path = new ArrayList<>();

                while(current != null){
                    path.add(current);
                    current = parents.get(current);
                }

                return path;

            } else {
                for (Node neighbour : current.getAdjacentNodes().keySet()) {
                        parents.put(neighbour, current);
                        queue.offer(neighbour);
                }
            }
        }
        return null;
    }

}