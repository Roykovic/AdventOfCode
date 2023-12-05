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

    public Long get(Long x) {

        for(Range key : this.keySet()){
            if(key.isInRange(x)){
                return this.get(key).getStart() + (x - key.getStart());
            }
        }

        return x;
    }
}
