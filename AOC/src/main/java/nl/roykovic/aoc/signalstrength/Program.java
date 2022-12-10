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

    public Map<Integer, Integer> run(){
        steps = new HashMap<>();
        int cycle = 1;
        int X = 1;
        for(CPUInstruction instruction : instructions){
            for(int step = 0; step < instruction.getAmountOfCycles(); step++){
                steps.put(cycle, X);
                cycle++;

                if(cycle % 40 == 0){
                    System.out.println("");
                }
                System.out.print("#");
            }
            X += instruction.getRegisterIncrease();
            steps.put(cycle, X);
        }

        return steps;
    }

    public int getSignalStrengthByCycles(Integer...cycles){
        int signalStrengthSum = 0;
        for(Integer cycle: cycles){
            int registerAmount = steps.get(cycle);
            signalStrengthSum += (cycle*registerAmount);
        }

        return signalStrengthSum;
    }
}
