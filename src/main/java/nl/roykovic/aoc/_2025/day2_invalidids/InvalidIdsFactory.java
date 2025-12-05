package nl.roykovic.aoc._2025.day2_invalidids;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class InvalidIdsFactory {
    public long generate(String input, boolean part2) {
        List<String> ranges = List.of(input.split(","));
        List<Long> invalidIds = new ArrayList<>();

        for(String range : ranges){
            Long start = Long.valueOf((range.split("-")[0]));
            long end = Long.parseLong(range.split("-")[1]);

            while(start <= end){

                String current = String.valueOf(start);

                if(!part2) {
                    String currentStart = current.substring(0, current.length() / 2);
                    String currentEnd = current.substring(current.length() / 2);

                    if (currentStart.equals(currentEnd)) {
                        invalidIds.add(start);
                    }
                }
                else {
                    if(current.matches("(\\d*)\\1+")){
                        invalidIds.add(start);
                    }
                }

                start++;
            }
        }

        return invalidIds.stream().mapToLong(Long::longValue).sum();

    }
}
