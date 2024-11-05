package nl.roykovic.aoc._2015.day23_turingmachine.operations;

public class HalfOperation extends TuringOperation{
    @Override
    public Double AddMultiplier(Double val) {
        return val/2;
    }
}
