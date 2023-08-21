package nl.roykovic.aoc._2022.day10_signalstrength;

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
