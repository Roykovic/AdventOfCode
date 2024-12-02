package nl.roykovic.aoc._2024.day2_unusualdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class UnusualDataFactory {
    public int countSafe(Stream<String> input, boolean dampening) {
        return (int) input
                .map(it -> it.split(" "))
                .map(it -> Arrays.stream(it).map(Integer::parseInt).toList())
                .map(it -> isSafe(it, dampening))
                .filter(it -> it)
                .count();
    }


    private boolean isSafe(List<Integer> report, boolean dampening){
        int modifier = report.get(report.size()-1).compareTo(report.get(0));

        for(int i = 0; i< report.size() -1; i++){
            Integer a = report.get(i);
            Integer b = report.get(i+1);

           if(b.compareTo(a) != modifier || Math.abs(b-a) < 1 || Math.abs(b-a) > 3){
               if(dampening){
                   List<Integer> newListA = new ArrayList<>(report);
                   newListA.remove(i);

                   List<Integer> newListB= new ArrayList<>(report);
                   newListB.remove(i+1);

                   return isSafe(newListA, false) || isSafe(newListB, false);
               }
               return false;
           }
        }
        return true;
    }
}
