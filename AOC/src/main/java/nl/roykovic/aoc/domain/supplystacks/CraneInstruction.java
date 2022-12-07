package nl.roykovic.aoc.domain.supplystacks;

public class CraneInstruction {
    private final int amountOfCrates;
    private final int originStack;
    private final int destinationStack;

    public CraneInstruction(int amountOfCrates, int originStack, int destinationStack) {
        this.amountOfCrates = amountOfCrates;
        this.originStack = originStack;
        this.destinationStack = destinationStack;
    }

    public int getAmountOfCrates() {
        return amountOfCrates;
    }

    public int getOriginStack() {
        return originStack;
    }

    public int getDestinationStack() {
        return destinationStack;
    }
}
