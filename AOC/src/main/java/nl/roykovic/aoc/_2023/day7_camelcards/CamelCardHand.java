package nl.roykovic.aoc._2023.day7_camelcards;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelCardHand{
    private String hand;
    private int bid;
    private CardHand cardHand;

    public CamelCardHand(String hand, int bid, boolean useJoker) {
        this.hand = hand;
        this.bid = bid;
        this.cardHand = this.calculateCardHand(useJoker);
    }

    private CardHand calculateCardHand(boolean useJoker){

        Map<Character,Long> occurencesMap = hand.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Long jokerValue = 0L;
        if(useJoker){
            jokerValue = occurencesMap.remove('J');
        }

        List<Integer> occurences = new java.util.ArrayList<>(occurencesMap.values().stream().map(Math::toIntExact).toList());
        occurences.sort(Collections.reverseOrder());
       if(useJoker && jokerValue != null){
           int highestValue = 0;
           if(!occurences.isEmpty()){
               highestValue = occurences.get(0);
               occurences.remove(0);
           }
               occurences.add(0, (int) (highestValue + jokerValue));
       }

        return switch (occurences.get(0)) {
            case 5 -> CardHand.FIVE_OF_A_KIND;
            case 4 -> CardHand.FOUR_OF_A_KIND;
            case 3 -> occurences.contains(2) ? CardHand.FULL_HOUSE : CardHand.THREE_OF_A_KIND;
            case 2 -> Collections.frequency(occurences, 2) > 1 ? CardHand.TWO_PAIR : CardHand.ONE_PAIR;
            default -> CardHand.HIGH_CARD;
        };
    }

    public String getHand() {
        return hand;
    }

    public int getBid() {
        return bid;
    }

    public CardHand getCardHand() {
        return cardHand;
    }
}