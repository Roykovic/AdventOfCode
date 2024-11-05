package nl.roykovic.aoc._2015.day23_turingmachine.operations;

public class JumpOneOperation extends TuringOperation{
    @Override
    public boolean condition(Double val) {
        return val == 1;
    }
}
