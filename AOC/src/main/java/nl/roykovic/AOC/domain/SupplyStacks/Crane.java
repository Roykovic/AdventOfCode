package nl.roykovic.AOC.domain.SupplyStacks;

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
            CrateStack originStack = crateStacks.get(instruction.getOriginStack() -1);
            CrateStack destinationStack = crateStacks.get(instruction.getDestinationStack() -1);

            for(int i = 0; i < instruction.getAmountOfCrates(); i++){
                destinationStack.push(originStack.pop());
            }
        }
    }

    public void printTopCrates(){
        for(CrateStack crateStack : crateStacks){
            System.out.print(crateStack.pop());
        }
    }
}
