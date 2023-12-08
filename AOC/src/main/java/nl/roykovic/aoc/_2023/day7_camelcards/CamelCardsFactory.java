package nl.roykovic.aoc._2023.day7_camelcards;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class CamelCardsFactory {

    public List<CamelCardHand> generate(Stream<String> input, boolean useJoker){
        List<CamelCardHand> output = new java.util.ArrayList<>(input.map(it -> new CamelCardHand(it.split(" ")[0], Integer.parseInt(it.split(" ")[1]), useJoker)).toList());

        if(useJoker){
        output.sort(new CamelCardComparator(List.of('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')));

        }
        else {
            output.sort(new CamelCardComparator(List.of('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')));
        }
        return output;
    }

    private record CamelCardComparator(List<Character> values) implements Comparator<CamelCardHand> {

        @Override
            public int compare(CamelCardHand o1, CamelCardHand o2) {
                int compare = o1.getCardHand().compareTo(o2.getCardHand());
                int i = 0;
                while (compare == 0) {
                    compare = Integer.compare(values.indexOf(o2.getHand().charAt(i)), values.indexOf(o1.getHand().charAt(i)));
                    i++;
                }
                return compare;
            }
        }
}
