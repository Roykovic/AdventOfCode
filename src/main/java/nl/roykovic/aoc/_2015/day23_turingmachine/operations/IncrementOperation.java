package nl.roykovic.aoc._2015.day23_turingmachine.operations;

public class IncrementOperation extends TuringOperation{
    @Override
    public Double AddMultiplier(Double val) {
        return val+1;
    }
}
