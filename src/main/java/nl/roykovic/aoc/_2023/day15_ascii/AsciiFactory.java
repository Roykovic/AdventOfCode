package nl.roykovic.aoc._2023.day15_ascii;

import java.util.Arrays;
import java.util.stream.Stream;

public class AsciiFactory {
    public long generate(String input) {
        return Arrays.stream(input.split(",")).mapToLong(this::getHashAlgorithmValue).sum();
    }

    public long getHashAlgorithmValue(String s) {
        long currentValue = 0;
        for (char c : s.toCharArray()) {
            currentValue += c;
            currentValue = currentValue * 17;
            currentValue = currentValue % 256;
        }
        return currentValue;
    }
}
