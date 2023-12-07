package nl.roykovic.aoc._2023.day7_camelcards;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class CamelCardsFactory {

    public List<CamelCardHand> generate(Stream<String> input){
        List<CamelCardHand> output = new java.util.ArrayList<>(input.map(it -> new CamelCardHand(it.split(" ")[0], Integer.parseInt(it.split(" ")[1]))).toList());
        output.sort(new CamelCardComparator());
        return output;
    }

    private static class CamelCardComparator implements Comparator<CamelCardHand>{
        @Override
        public int compare(CamelCardHand o1, CamelCardHand o2) {
            int compare = o1.getCardHand().compareTo(o2.getCardHand());
            List<Character> values = List.of('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2');

            int i = 0;
            while(compare == 0){
                compare = Integer.compare(values.indexOf(o2.getHand().charAt(i)), values.indexOf(o1.getHand().charAt(i)));
                i++;
            }
            return compare;
        }
    }
}
