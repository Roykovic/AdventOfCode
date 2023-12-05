package nl.roykovic.aoc._2023.day5_garden;

import java.util.HashMap;
import java.util.Map;

public class RangeMap extends HashMap<Range, Range> {

    public RangeMap(Map<Range, Range> result) {
        super(result);
    }

    @Override
    public Range get(Object key) {
        return super.get(key);
    }

    public Range getRelevantRange(Range range){
        long start = 0;
        long end = 0;
        for(Range key : this.keySet()){
            if(key.isInRange(range.getStart()) || key.isInRange(range.getEnd())) {
                if (key.isInRange(range.getStart())) {
                    start = this.get(key).getStart() + (range.getStart() - key.getStart());
                }
                else{
                    start = this.get(range.getStart());
                }
                if (key.isInRange(range.getEnd())) {
                    end = this.get(key).getStart() + (range.getEnd() - key.getStart());
                }
                else{
                    end = this.get(range.getEnd());
                }
            }
        }

        return new Range(start, end);
    }

    public Long get(Long x) {

        for(Range key : this.keySet()){
            if(key.isInRange(x)){
                return this.get(key).getStart() + (x - key.getStart());
            }
        }

        return x;
    }
}
