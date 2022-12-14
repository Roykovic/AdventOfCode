package nl.roykovic.aoc._2022.signalstrength;

public class CPUInstruction {

    private final int amountOfCycles;
    private final int registerIncrease;

    public CPUInstruction(InstructionType instructionType, int registerIncrease) {
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
