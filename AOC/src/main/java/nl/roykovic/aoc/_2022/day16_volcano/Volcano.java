package nl.roykovic.aoc._2022.day16_volcano;

import java.util.List;

public class Volcano {
    private int timeBeforeEruption = 30;
    private int pressureReleased = 0;
    private List<Valve> valves;

    public int getTimeBeforeEruption() {
        return timeBeforeEruption;
    }

    public void setTimeBeforeEruption(int timeBeforeEruption) {
        this.timeBeforeEruption = timeBeforeEruption;
    }

    public int getPressureReleased() {
        return pressureReleased;
    }

    public void setPressureReleased(int pressureReleased) {
        this.pressureReleased = pressureReleased;
    }

    public List<Valve> getValves() {
        return valves;
    }

    public void setValves(List<Valve> valves) {
        this.valves = valves;
    }
}
