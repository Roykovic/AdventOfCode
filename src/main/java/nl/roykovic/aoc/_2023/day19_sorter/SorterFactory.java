package nl.roykovic.aoc._2023.day19_sorter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class SorterFactory {
    public int generate(List<String> input) {

        var blankLine = input.indexOf("");

        var workFlows = input.subList(0, blankLine);
        var parts = input.subList(blankLine+1,input.size());

        return 0;
    }
}
