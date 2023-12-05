package nl.roykovic.aoc._2023.day5_garden;

public class Range {
    private long start;
    private long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public boolean isInRange(long x){
        return start < x && x < end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
