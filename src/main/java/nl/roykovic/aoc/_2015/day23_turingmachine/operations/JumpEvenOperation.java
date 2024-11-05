package nl.roykovic.aoc._2015.day23_turingmachine.operations;

public class JumpEvenOperation extends TuringOperation{
    @Override
    public boolean condition(Double val) {
        return val % 2 == 0;
    }
}
