package nl.roykovic.aoc._2023.day19_sorter;

import nl.roykovic.aoc._2023.day5_garden.Range;
import org.apache.commons.lang3.SerializationUtils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class RangePart {
    Map<Character, Range> values;

    public RangePart(Map<Character, Range> values) {
        this.values = values;
    }

    public BigInteger getPossibilities(){
        return values.values().stream().mapToLong(Range::getLength).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).orElseThrow();
    }

    public RangePart merge(RangePart otherRangePart){
        RangePart rp = new RangePart(SerializationUtils.clone(new HashMap<>(values)));

        for(var value : values.entrySet()){

            var key = value.getKey();
            var val = value.getValue();
            var otherVal = otherRangePart.values.get(key);

            if(val.equals(otherVal)){
                rp.values.put(value.getKey(), value.getValue());
            }
            else {
                var minStart = Math.min(val.getStart(), otherVal.getStart());
                var maxEnd = Math.max(val.getEnd(), otherVal.getEnd());

                rp.values.put(key, new Range(minStart, maxEnd));
            }
        }
        return rp;
    }
}
