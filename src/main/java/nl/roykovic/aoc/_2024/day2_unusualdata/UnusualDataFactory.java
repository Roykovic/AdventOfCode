package nl.roykovic.aoc._2024.day2_unusualdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class UnusualDataFactory {
    public int countSafe(Stream<String> input) {
        return (int) input
                .map(it -> it.split(" "))
                .map(it -> Arrays.stream(it).map(Integer::parseInt).toList())
                .map(this::isSafe)
                .filter(it -> it)
                .count();
    }
    public int countSafeWithDampener(Stream<String> input) {
        return (int) input
                .map(it -> it.split(" "))
                .map(it -> Arrays.stream(it).map(Integer::parseInt).toList())
                .map(this::isSafeWithDampening)
                .filter(it -> it)
                .count();
    }


    private boolean isSafe(List<Integer> report){
        int modifier = report.get(report.size()-1).compareTo(report.get(0));

        for(int i = 0; i< report.size() -1; i++){
            Integer a = report.get(i);
            Integer b = report.get(i+1);

           if(b.compareTo(a) != modifier || Math.abs(b-a) < 1 || Math.abs(b-a) > 3){
               return false;
           }
        }
        return true;
    }

    private boolean isSafeWithDampening(List<Integer> report){
        int modifier = report.get(report.size()-1).compareTo(report.get(0));

        for(int i = 0; i< report.size() -1; i++){
            Integer a = report.get(i);
            Integer b = report.get(i+1);

            if(b.compareTo(a) != modifier || Math.abs(b-a) < 1 || Math.abs(b-a) > 3){

                List<Integer> newListA = new ArrayList<>(report);
                newListA.remove(i);

                List<Integer> newListB= new ArrayList<>(report);
                newListB.remove(i+1);

                return isSafe(newListA) || isSafe(newListB);
            }
        }
        return true;
    }
}
