package nl.roykovic.aoc._2023.day7_camelcards;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelCardHand{
    private String hand;
    private int bid;
    private CardHand cardHand;

    public CamelCardHand(String hand, int bid) {
        this.hand = hand;
        this.bid = bid;
        this.cardHand = this.calculateCardHand();
    }

    private CardHand calculateCardHand(){
        Map<Character, Long> occurences = hand.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if(occurences.containsValue(5L)){
            return CardHand.FIVE_OF_A_KIND;
        }
        if(occurences.containsValue(4L)){
            return CardHand.FOUR_OF_A_KIND;
        }
        if(occurences.containsValue(3L)){
            if(occurences.containsValue(2L)){
                return CardHand.FULL_HOUSE;
            }
            return CardHand.THREE_OF_A_KIND;
        }
        if(occurences.containsValue(2L)){
            return Collections.frequency(occurences.values(),2L) > 1? CardHand.TWO_PAIR: CardHand.ONE_PAIR;
        }
        return CardHand.HIGH_CARD;
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