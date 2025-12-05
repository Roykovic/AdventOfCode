package nl.roykovic.aoc._2025.day5_cafetaria;

import nl.roykovic.aoc._2023.day5_garden.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class CafetariaFactory {
    public int generate(List<String> input) {

        List<Range> ranges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();


        for(String line : input){
            if(line.contains("-")) {
                String[] parts = line.split("-");
                Range range = new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                ranges.add(range);
            } else if (!line.isBlank()) {
                ids.add(Long.parseLong(line));
            }
        }
        int count = 0;
        for(Long id : ids){
           if(ranges.stream().anyMatch(it -> it.isInRange(id))){
               count++;
           };
        }

        return count;
    }
}
