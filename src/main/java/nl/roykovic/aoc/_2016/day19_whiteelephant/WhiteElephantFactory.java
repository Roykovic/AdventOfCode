package nl.roykovic.aoc._2016.day19_whiteelephant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class WhiteElephantFactory {
    public int generate(int input) {

        int[] elves = IntStream.range(1,input+1).toArray();
        boolean carryOver = elves.length % 2 != 0;

        elves = Arrays.stream(elves).filter(it -> it % 2 != 0).toArray();
        while(elves.length > 1){

            List<Integer> newElves = new ArrayList<>();

            for(int i = 0; i < elves.length; i++){
                if(carryOver && i % 2 != 0){
                    newElves.add(elves[i]);
                }
                else if(!carryOver && i % 2 == 0){
                    newElves.add(elves[i]);
                }
            }
            carryOver = elves.length % 2 != 0;
            elves = newElves.stream().mapToInt(it -> it).toArray();
        }

        return Arrays.stream(elves).findFirst().orElseThrow();
    }
}
