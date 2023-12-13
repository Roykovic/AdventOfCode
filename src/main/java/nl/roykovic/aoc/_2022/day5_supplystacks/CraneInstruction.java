package nl.roykovic.aoc._2022.day5_supplystacks;

import java.util.List;
import java.util.Objects;

public final class CraneInstruction {
    private final int amountOfCrates;
    private final int originStack;
    private final int destinationStack;

    public CraneInstruction(int amountOfCrates, int originStack, int destinationStack) {
        this.amountOfCrates = amountOfCrates;
        this.originStack = originStack;
        this.destinationStack = destinationStack;
    }

    public CraneInstruction(List<Integer> amounts) {
        if (amounts.size() != 3) {
            throw new RuntimeException("Expected three values");
        }
        this.amountOfCrates = amounts.get(0);
        this.originStack = amounts.get(1);
        this.destinationStack = amounts.get(2);
    }

    public int amountOfCrates() {
        return amountOfCrates;
    }

    public int originStack() {
        return originStack;
    }

    public int destinationStack() {
        return destinationStack;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (CraneInstruction) obj;
        return this.amountOfCrates == that.amountOfCrates &&
                this.originStack == that.originStack &&
                this.destinationStack == that.destinationStack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfCrates, originStack, destinationStack);
    }

    @Override
    public String toString() {
        return "CraneInstruction[" +
                "amountOfCrates=" + amountOfCrates + ", " +
                "originStack=" + originStack + ", " +
                "destinationStack=" + destinationStack + ']';
    }

}
