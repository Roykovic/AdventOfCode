package nl.roykovic.aoc._2022.volcano;

import java.util.List;
import java.util.Map;

public class Valve {
    private final int flowRate;
    private List<Valve> neighbours;

    private boolean open = false;

    private boolean visited = false;

    public Valve(int flowRate) {
        this.flowRate = flowRate;
    }

    public Map.Entry<Valve, Integer> getMostOptimalValveFromHere(int minutesLeft, int highestFlowRate){
        minutesLeft--;
        Map.Entry<Valve, Integer> highestFound = null;

        if(visited) return null;
        visited = true;
        if(flowRate * minutesLeft > highestFlowRate) {
            highestFlowRate = flowRate * minutesLeft;
            highestFound = Map.entry(this, flowRate * minutesLeft);
        }
        if(minutesLeft == 0) return highestFound;
        for(Valve neighbour : neighbours){
            Map.Entry<Valve, Integer> highestNeighbour = neighbour.getMostOptimalValveFromHere(minutesLeft, highestFlowRate);
            if(highestNeighbour != null && highestNeighbour.getValue() > highestFlowRate){
                highestFound = highestNeighbour;
            };
        }
        return highestFound;
    }

    public int getFlowRate() {
        return flowRate;
    }

    public List<Valve> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Valve> neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
