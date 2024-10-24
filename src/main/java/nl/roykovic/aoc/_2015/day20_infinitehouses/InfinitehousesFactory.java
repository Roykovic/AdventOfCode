package nl.roykovic.aoc._2015.day20_infinitehouses;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
public class InfinitehousesFactory {
    public int calculatePresents(int houseNumber) {
        Set<Integer> factors = new HashSet<>();
        int step = houseNumber % 2 == 0 ? 1 : 2;
        for (int i = 1; i <= Math.sqrt(houseNumber); i += step) {
            if (houseNumber % i == 0) {
                factors.add(i);
                factors.add(houseNumber / i);
            }
        }
        return factors.stream().mapToInt(it -> it).sum() *10;
    }

    public int calculatePresentsTwo(int houseNumber) {
        int step = houseNumber % 2 == 0 ? 1 : 2;

        int counter = 0;

        for (int i = 1; i <= houseNumber; i+=step) {
            if (houseNumber % i == 0 && houseNumber > i * 50) {
                counter += i;
            }
        }
        return counter * 11;
    }
}
