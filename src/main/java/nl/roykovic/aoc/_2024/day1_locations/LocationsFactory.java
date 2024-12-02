package nl.roykovic.aoc._2024.day1_locations;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class LocationsFactory {

    List<Integer> one = new ArrayList<>();
    List<Integer> two = new ArrayList<>();

    private void initLists(Stream<String> input){
        List<String> oneS = new ArrayList<>();
        List<String> twoS = new ArrayList<>();

        input.map(it -> it.split("   "))
                .peek(it -> {
                    oneS.add(it[0]);
                    twoS.add(it[1]);
                })
                .toList();

        one = oneS.stream().map(Integer::parseInt).sorted().toList();
        two = twoS.stream().map(Integer::parseInt).sorted().toList();
    }


    public int generate(Stream<String> input) {

        initLists(input);

        int distance = 0;

        for(int i = 0; i< one.size(); i++){
            distance += Math.abs(one.get(i) - two.get(i));
        }

        return distance;
    }

    public int countMatches(Stream<String> input){
        initLists(input);

        int similarityScore = 0;

        for(int i : one){
            int matches = (int) two.stream().filter(it -> it == i).count();

            similarityScore += (i*matches);
        }

        return similarityScore;
    }
}
