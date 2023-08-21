package nl.roykovic.aoc.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TSPService {

    public static List<Integer> calculateDistances(int[][] distanceChart, boolean circular){

        //make a list with consecutive integers by the indexes of the distancechart
        List<Integer> indexList = IntStream.range(0, distanceChart.length).boxed().toList();

        //Calculate all possible orders of the list
        List<List<Integer>> possiblePermutations = permutations(indexList).toList();

        List<Integer> distances = new ArrayList<>();

        //now for every possible order of nodes, calculate how long each route is.
        //We do this by getting all distances from the lookup table from this node in the list to the next.
        //Until we hit the end of the route
        for (List<Integer> possibleRoute : possiblePermutations) {
            int distance = 0;
            for (int i = 0; i < possibleRoute.size(); i++) {
                if (i < (possibleRoute.size() - 1)) {
                    int currentNode = possibleRoute.get(i);
                    int nextNode = possibleRoute.get(i + 1);

                    distance += distanceChart[currentNode][nextNode];
                }
                else if(circular){
                    int currentNode = possibleRoute.get(i);
                    int nextNode = possibleRoute.get(0);

                    distance += distanceChart[currentNode][nextNode];
                }
            }
            distances.add(distance);
        }

        return distances;
    }
    public static Stream<List<Integer>> permutations(List<Integer> input) {
        if (input.size() == 1) {
            return Stream.of(new LinkedList<>(input));
        }
        return input.stream()
                .flatMap(first -> permutations(input.stream()
                        .filter(a -> !a.equals(first))
                        .toList())
                        .map(LinkedList::new)
                        .peek(l -> l.addFirst(first)));
    }
}
