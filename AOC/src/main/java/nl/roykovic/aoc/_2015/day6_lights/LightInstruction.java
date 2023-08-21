package nl.roykovic.aoc._2015.day6_lights;

public enum LightInstruction {
    OFF(-1, 0),
    ON(1, 1),
    TOGGLE(2, -1);

    private final int brightnessValue;
    private final int switchedValue;

    LightInstruction(int brightnessValue, int switchedValue){
        this.brightnessValue = brightnessValue;
        this.switchedValue = switchedValue;
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

    public int getBrightnessValue() {
        return brightnessValue;
    }

    public int getSwitchedValue() {
        return switchedValue;
    }
}
