package nl.roykovic.aoc._2023.day11_galaxies;

import nl.roykovic.aoc._2022.day12_hillclimb.Node;
import nl.roykovic.aoc.utils.Coord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GalaxyFactory {
    public long generate(List<String> input){

        input = expand(input);

        List<Coord> galaxies = new ArrayList<>();
        for(int y = 0; y< input.size(); y++){
            for(int x =0; x< input.get(y).length(); x++){
                if(input.get(y).charAt(x) == '#'){
                    galaxies.add(new Coord(x,y));
                }
            }
        }
        long sum = 0;

        for(Coord c : galaxies){
            sum += galaxies.stream().filter(it -> !it.equals(c)).mapToLong(c::manhattanDistance).sum();
        }

        return sum/2;
    }

    private List<String> expand(List<String> input){
        List<String> expandedRows = new ArrayList<>();
        for(int i = 0; i< input.size(); i++){
            String currentRow = input.get(i);

            expandedRows.add(currentRow);
            if(!input.get(i).contains("#")){
                expandedRows.add(currentRow);
            }
        }

        List<Integer> emptyColumns = new ArrayList<>();
        int length = expandedRows.get(0).length();
        for(int i =0 ; i<length; i++){
            boolean containsGalaxies = false;
            for(String s : expandedRows){
                if (s.charAt(i) == '#') {
                    containsGalaxies = true;
                    break;
                }
            }
            if(!containsGalaxies){
                emptyColumns.add(i);
            }
        }

        for(int i =0; i < expandedRows.size(); i++){
            for(int j = 0; j< emptyColumns.size(); j++){
                expandedRows.set(i, new StringBuilder(expandedRows.get(i)).insert(emptyColumns.get(j) +j, ".").toString());
            }
        }

        return expandedRows;
    }

    private void setNeighbours(List<Node> nodeList) {
        for (Node node : nodeList) {
            int curX = node.getX();
            int curY = node.getY();
            int curElevation = node.getElevation();

            Map<Node, Integer> neighbours = nodeList.stream().filter(n -> {
                int dX = Math.abs(n.getX() - curX);
                int dY = Math.abs(n.getY() - curY);

                int dXY = Math.abs(dX - dY);

                int dElevation = n.getElevation() - curElevation;

                return dX <= 1 && dY <= 1 && dXY > 0 && dElevation <= 1; //if x and y are 1 or less away, but not both, and elevation is 1 higher or (any amount) lower, this is a neighbour
            }).collect((Collectors.toMap(Function.identity(), it -> 1)));

            node.setAdjacentNodes(neighbours);
        }
    }
}
