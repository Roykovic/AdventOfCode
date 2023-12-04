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

    public int generateCards(Stream<String> input){
        List<Integer> numberOfWins = getWinningNumbers(input)
                .toList();
        
        int[] cards = new int[numberOfWins.size()];
        Arrays.fill(cards, 1);

        for(int i = 0; i < numberOfWins.size(); i++){
            int num = numberOfWins.get(i);
            for(int j = 0; j< cards[i]; j++) {
                for (int card = i + 1; card < num + i + 1; card++) {
                    cards[card]++;
                }
            }
        }

        return Arrays.stream(cards).sum();
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
