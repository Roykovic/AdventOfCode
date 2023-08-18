package nl.roykovic.aoc._2015.day6_lights;

public enum LightInstruction {
    OFF(-1),
    ON(1),
    TOGGLE(2);

    private final int value;

    LightInstruction(int value){
        this.value = value;
    }

    static LightInstruction getIntructionFromString(String instruction){
        if(instruction.contains("off")){
            return OFF;
        }
        else if(instruction.contains("on")){
            return ON;
        }
        return TOGGLE;
    }

    public int getValue() {
        return value;
    }
}
