package nl.roykovic.aoc.signalstrength;

@SuppressWarnings("unused")
public enum InstructionType {
    addx(2),
    noop(1);

    private final int amountOfCycles;

    InstructionType(int amountOfCycles) {
        this.amountOfCycles = amountOfCycles;
    }

    public int getAmountOfCycles() {
        return amountOfCycles;
    }
}
