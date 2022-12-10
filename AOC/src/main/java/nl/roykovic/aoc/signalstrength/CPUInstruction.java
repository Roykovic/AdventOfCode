package nl.roykovic.aoc.signalstrength;

public class CPUInstruction {

    private final InstructionType instructionType;
    private final int amountOfCycles;
    private final int registerIncrease;

    public CPUInstruction(InstructionType instructionType, int registerIncrease) {
        this.instructionType = instructionType;
        this.amountOfCycles = instructionType.getAmountOfCycles();
        this.registerIncrease = registerIncrease;
    }

    public int getAmountOfCycles() {
        return amountOfCycles;
    }

    public int getRegisterIncrease() {
        return registerIncrease;
    }
}
