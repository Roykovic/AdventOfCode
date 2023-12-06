package nl.roykovic.aoc._2023.day5_garden;

public class Range {
    private long start;
    private long end;

    private String operation;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public boolean isInRange(long x){
        return start < x && x < end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
