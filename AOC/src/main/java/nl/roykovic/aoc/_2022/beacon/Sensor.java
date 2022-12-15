package nl.roykovic.aoc._2022.beacon;

import nl.roykovic.aoc.utils.Coord;

public class Sensor {
    private final Coord coord;
    private final Beacon closestBeacon;

    public Sensor(Coord coord, Beacon closestBeacon) {
        this.coord = coord;
        this.closestBeacon = closestBeacon;
    }

    public long manhattanDistanceToBeacon(){
     return coord.manhattanDistance(closestBeacon.getCoord());
    }

    public boolean isInSignalRange(Coord coord){
        return coord.manhattanDistance(this.coord) <= this.manhattanDistanceToBeacon();
    }

    public Beacon getClosestBeacon() {
        return closestBeacon;
    }

    public Coord getCoord() {
        return coord;
    }
}
