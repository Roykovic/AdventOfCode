package nl.roykovic.AOC.domain;

public class CleanupRange {
    private final int start;
    private final int end;

    public CleanupRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean contains(CleanupRange otherRange){
        return start <= otherRange.start && end >= otherRange.end;
    }

    public boolean overlaps(CleanupRange otherRange){
        return (otherRange.start >= start && otherRange.start <= end) || (otherRange.end >= start && otherRange.end <= end);
    }
}
