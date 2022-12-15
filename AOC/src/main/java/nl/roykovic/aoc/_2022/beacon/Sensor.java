package nl.roykovic.aoc._2022.beacon;

import nl.roykovic.aoc.utils.Coord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    List<Coord> signalRangePerimeter(long maxX, long maxY){
        List<Coord> perimeter = new ArrayList<>();
        long upperY = getCoord().getY();
        long lowerY = getCoord().getY();

        long minX = Math.max(0, coord.getX() - manhattanDistanceToBeacon() -1);

        maxX = Math.min(maxX,coord.getX() + manhattanDistanceToBeacon() +2);

        for(long x =minX; x < maxX; x++){

            perimeter.add(new Coord(x, upperY));

            if (upperY > 0 && lowerY > 0 && upperY < maxY && lowerY < maxY) {

                if (upperY != lowerY) {
                    perimeter.add(new Coord(x, lowerY));
                }

                if (x < coord.getX()) {
                    upperY--;
                    lowerY++;
                } else {
                    upperY++;
                    lowerY--;
                }
            }
        }
        return perimeter;
    }

    List<Coord> signalRangePerimeter(){
        List<Coord> perimeter = new ArrayList<>();
        long upperY = getCoord().getY();
        long lowerY = getCoord().getY();
        for(long x = coord.getX() - manhattanDistanceToBeacon() -1; x < coord.getX() + manhattanDistanceToBeacon() +2; x++){

            perimeter.add(new Coord(x, upperY));

            if(upperY != lowerY){
                perimeter.add(new Coord(x, lowerY));
            }

            if(x < coord.getX()){
                upperY--;
                lowerY++;
            }
            else{
                upperY++;
                lowerY--;
            }
        }
        return perimeter;
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
