package nl.roykovic.aoc._2023.day8_maps;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapsFactory {
    public int generate(List<String> input){
        String directions = input.get(0);

        var nodes = input.stream().skip(2)
                .map(it ->
                        Arrays.stream(
                                it.split(" = "))
                                .map(str -> {
                                    str = str.replaceAll("[()]","");
                                    return str.split(", ");
                }).toList())
                .collect(Collectors.toMap(it -> it.get(0)[0], it-> new MapNode(it.get(1)[0], it.get(1)[1])));

        String startNode = "AAA";
        String endNode = "ZZZ";
        String currentNode = startNode;

        int step = 0;
        while(!Objects.equals(currentNode, endNode)){
            char currentDirection = directions.charAt(step % directions.length());
            if(currentDirection == 'L'){
                currentNode = nodes.get(currentNode).getLeft();
            }
            else{
                currentNode = nodes.get(currentNode).getRight();
            }
            step++;
        }

        return step;
    }

    private static class MapNode{
        private final String left;
        private final String right;

        public MapNode(String left, String right) {
            this.left = left;
            this.right = right;
        }

        public String getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }
    }
}
