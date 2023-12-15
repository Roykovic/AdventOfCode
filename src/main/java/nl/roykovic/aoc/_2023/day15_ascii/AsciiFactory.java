package nl.roykovic.aoc._2023.day15_ascii;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AsciiFactory {

    Map<String, Integer>[] boxes = new LinkedHashMap[256];

    public long generate(String input) {

        Arrays.stream(input.split(",")).forEach(this::runInstruction);

        return IntStream.range(0, boxes.length).mapToObj(
                        box -> {
                            int boxSize = Optional.ofNullable(boxes[box]).map(Map::size).orElse(0);
                            return IntStream.range(0, boxSize).map(slot -> {
                                int boxNumber = (box + 1);
                                int slotNumber = slot + 1;
                                int vocalLength = boxes[box].values().toArray(Integer[]::new)[slot];

                                return boxNumber * slotNumber * vocalLength;
                            });
                        })
                .flatMapToInt(it -> it)
                .sum();
    }

    private void runInstruction(String instruction) {

        String label = instruction.replaceAll("\\P{L}+", "");
        int box = (int) getHashAlgorithmValue(label);
        if (boxes[box] == null) {
            boxes[box] = new LinkedHashMap<>();
        }
        if (instruction.contains("=")) {
            int vocalLength = Integer.parseInt(StringUtils.getDigits(instruction));
            boxes[box].put(label, vocalLength);

        } else {
            boxes[box].remove(label);
        }
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
