package nl.roykovic.aoc._2022.beacon;

import nl.roykovic.aoc.utils.Coord;

import java.util.ArrayList;
import java.util.List;

public record Sensor(Coord coord, Beacon closestBeacon) {

    public long manhattanDistanceToBeacon() {
        return coord.manhattanDistance(closestBeacon.coord());
    }

    List<Coord> signalRangePerimeter(long maxX, long maxY) {
        List<Coord> perimeter = new ArrayList<>();
        long upperY = coord().getY();
        long lowerY = coord().getY();

        long minX = Math.max(0, coord.getX() - manhattanDistanceToBeacon() - 1);

        maxX = Math.min(maxX, coord.getX() + manhattanDistanceToBeacon() + 2);

        for (long x = minX; x < maxX; x++) {

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

    public boolean isInSignalRange(Coord coord) {
        return coord.manhattanDistance(this.coord) <= this.manhattanDistanceToBeacon();
    }
}
