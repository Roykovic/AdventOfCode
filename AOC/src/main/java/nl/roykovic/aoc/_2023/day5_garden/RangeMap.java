package nl.roykovic.aoc._2023.day5_garden;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RangeMap extends HashMap<Range, Range> {

    public RangeMap(Map<Range, Range> result) {
        super(result);
    }

    public Long get(Long x) {
        for(Range key : this.keySet()){
            if(key.isInRange(x)){
                return this.get(key).getStart() + (x - key.getStart());
            }
        }

        return x;
    }

    public Range getRange(Long x) {
        for(Range key : this.keySet()){
            if(key.isInRange(x)){
                return key;
            }
        }

        return null;
    }

    public List<Range> getMappedRanges(Range input){
        List<Range> subset = getSubLists(input);
       return subset;
    }

    private List<Range> getSubLists(Range input){
        List<Range> foundInputRanges = new ArrayList<>();

        long startOffset = 0;
        long endOffset = 0;

        long lowest =  this.keySet().stream().mapToLong(Range::getStart).min().orElseThrow();
        long highest =  this.keySet().stream().mapToLong(Range::getEnd).max().orElseThrow();

        if(input.getStart() > highest || input.getEnd() < lowest){
            return List.of(input);
        }

        if(input.getStart() < lowest){
            foundInputRanges.add(new Range(input.getStart(), lowest));
            input.setStart(lowest);
        }

        if(input.getEnd() > highest){
            foundInputRanges.add(new Range(highest, input.getEnd()));
            input.setEnd(highest);
        }
        for(Range key : this.keySet()){
            if(key.isInRange(input.getStart())){
                startOffset = input.getStart() - key.getStart() ;

                Range outputRange = this.get(key);
                outputRange.setStart(outputRange.getStart()+startOffset);

                if(!key.isInRange(input.getEnd())){
                    foundInputRanges.addAll(getSubLists(new Range(key.getEnd(), input.getEnd())));
                }
                else{
                    endOffset = key.getEnd() - input.getEnd();
                    outputRange.setEnd(outputRange.getEnd() -endOffset);
                }
                foundInputRanges.add(outputRange);
            }
        }
        return foundInputRanges;
    }
}
