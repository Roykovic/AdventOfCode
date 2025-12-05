package nl.roykovic.aoc._2025.day3_batterybanks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class BatteryBanksFactory {

    private int length;

    public long generate(Stream<String> input, int length) {
        this.length = length;
        return input.mapToLong(this::getMaxJoltage).sum();
    }


    private long getMaxJoltage(String input){

        List<Integer> digits = input.chars().mapToObj(c -> (char) c - '0').collect(Collectors.toList());

        StringBuilder result = new StringBuilder();

        for(int iter = 0; iter < length; iter++) {
        int highest = 0;
        int highestIndex = -1;

            for (int i = 0; i < digits.size() - (length-(iter+1)); i++) {
                int current = digits.get(i);
                if (current > highest) {
                    highest = current;
                    highestIndex = i;
                }
            }
            result.append(highest);

           digits = digits.subList(highestIndex + 1, digits.size());

        }

//        System.out.println(Long.parseLong(result.toString()));

        return Long.parseLong(result.toString());
    }
}
