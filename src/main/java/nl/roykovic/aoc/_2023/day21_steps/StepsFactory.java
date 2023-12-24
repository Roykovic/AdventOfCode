package nl.roykovic.aoc._2023.day21_steps;

import nl.roykovic.aoc.utils.Coord;

import java.util.*;

public class StepsFactory {
    public int generate(List<String> input, int steps) {

        Map<Coord, Character> characterMap = new HashMap<>();

        Coord start = null;

        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                Coord c = new Coord(x, y);
                char ch = input.get(y).charAt(x);

                if (ch == 'S') {
                    start = new Coord(c);
                }

                characterMap.put(c, ch);
            }
        }

        assert start != null;
        Set<Coord> currentCoords = new HashSet<>(List.of(start));

        List<String> cachedStates = new ArrayList<>();

        for (int step = 0; step < steps; step++) {
            Set<Coord> nextCoords = new HashSet<>();

            for (Coord currentCoord : currentCoords) {

                nextCoords.addAll(
                        currentCoord.getNeighboursStraight().stream()
                                .filter(it -> !characterMap.get(it.wrapAround(input.get(0).length() - 1, input.size() - 1)).equals('#')).toList());
            }

            System.out.println(nextCoords.size() -currentCoords.size());

            currentCoords = nextCoords;
        }
        return currentCoords.size();
    }
}
