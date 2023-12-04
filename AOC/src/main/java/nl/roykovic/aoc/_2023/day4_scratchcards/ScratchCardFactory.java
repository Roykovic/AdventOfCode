package nl.roykovic.aoc._2023.day4_scratchcards;

import org.apache.commons.lang3.StringUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ScratchCardFactory {

    public int generatePoints(Stream<String> input){
        return getWinningNumbers(input)
                .mapToInt(winningNum ->(int) Math.floor(Math.pow(2.0, winningNum-1)))
                .sum();
    }

    private Stream<Integer> getWinningNumbers(Stream<String> input){
        return input
                .map(inputStr -> inputStr.split(":")[1]
                        .split("\\|"))
                .map(nums -> Arrays.stream(nums)
                        .map(num -> Arrays.stream(num
                                        .trim().split(" "))
                                .filter(StringUtils::isNotBlank).toList()).toList())
                .map(nums -> {
                    ArrayList<String> l = new ArrayList<>(nums.get(0));
                    l.retainAll(nums.get(1));
                    return l.size();
                });
    }
}
