package nl.roykovic.aoc.hillclimb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NodeFactory {

    public List<Node> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        List<Node> nodeList = new ArrayList<>();

        for(int y = 0; y < lines.size(); y++){
            String line = lines.get(y);
            for(int x = 0; x < line.length(); x++){
                char elevationChar = line.charAt(x);

                boolean isStart = false;
                boolean isEnd = false;

                if(elevationChar == 'S'){
                    elevationChar = 'a';
                    isStart = true;
                }
                else if(elevationChar == 'E'){
                    elevationChar = 'z';
                    isEnd = true;
                }
                nodeList.add(new Node(x, y, elevationChar - 'a', isStart, isEnd)); //make elevation an int, for easier maths and add new node to list
            }
        }

        setNeighbours(nodeList);
        return nodeList;
    }

    public static void setNeighbours(List<Node> nodeList){
        for(Node node : nodeList){
            int curX = node.getX();
            int curY = node.getY();
            int curElevation = node.getElevation();

            Map<Node, Integer> neighbours = nodeList.stream().filter(n -> {
                int dX = Math.abs(n.getX() - curX);
                int dY = Math.abs(n.getY() - curY);

                int dXY = Math.abs(dX - dY);

                int dElevation = n.getElevation() - curElevation;

                return dX <= 1 && dY <= 1 && dXY > 0 && dElevation <=1; //if x and y are 1 or less away, but not both, and elevation is 1 higher or (any amount) lower, this is a neighbour
            }).collect((Collectors.toMap(Function.identity(), it -> 1)));

            node.setAdjacentNodes(neighbours);
        }
    }
}
