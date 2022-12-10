package nl.roykovic.aoc.signalstrength;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {

    private final List<CPUInstruction> instructions;

    private Map<Integer, Integer> steps;

    public Program(List<CPUInstruction> instructions) {
        this.instructions = instructions;
    }

}
