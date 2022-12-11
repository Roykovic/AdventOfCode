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

                draw(cycle, X); //each cycle a pixel should be drawn
                steps.put(cycle, X);
                cycle++;
            }
            X += instruction.getRegisterIncrease();
            steps.put(cycle, X);
        }

        return steps;
    }

    private void draw(int cycle, int X){

        int curPos = cycle -1; //all of a sudden positions are 0 indexed

        while(curPos > 39){ //X is cumulative, while the curPos is 0-39
            curPos -= 40;
        }

        if(Math.abs(curPos - X) <= 1){
            System.out.print("#");  //if the curPos is either 1 before, 1 after or at the X, draw a lit pixel (#)
        }
        else{
            System.out.print(".");  //if curPos is further away, draw a dark pixel (.)
        }
        if(cycle % 40 == 0){
            System.out.println(); //lines are 40px wide, so if curPos is divisble by 40, add linebreak
        }
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
