package nl.roykovic.aoc._2022.supplystacks;

import java.util.List;

public class Crane {
   private final List<CrateStack> crateStacks;
   private final List<CraneInstruction> instructions;

    public Crane(List<CrateStack> crateStacks, List<CraneInstruction> instructions) {
        this.crateStacks = crateStacks;
        this.instructions = instructions;
    }

    public void executeInstructions(){
        for(CraneInstruction instruction : instructions){
            CrateStack originStack = crateStacks.get(instruction.originStack() -1);
            CrateStack destinationStack = crateStacks.get(instruction.destinationStack() -1);

            for(int i = 0; i < instruction.amountOfCrates(); i++){
                destinationStack.push(originStack.pop());
            }
        }
    }

    public void executeImprovedInstructions(){
        for(CraneInstruction instruction : instructions){
            CrateStack originStack = crateStacks.get(instruction.originStack() -1);
            CrateStack destinationStack = crateStacks.get(instruction.destinationStack() -1);

            destinationStack.push(originStack.pop(instruction.amountOfCrates()));
        }
    }

    public void printTopCrates(){
        for(CrateStack crateStack : crateStacks){
            System.out.print(crateStack.pop());
        }
    }
}
