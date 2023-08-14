package nl.roykovic.aoc.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Face {
    Coord[] coords;

    public Face(Coord... coords) {
        this.coords = coords;
    }

    public Coord[] getCoords() {
        return coords;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Face)) {
            return false;
        }

        Face f = (Face) o;


        return new HashSet<>(Arrays.asList(f.getCoords())).containsAll(Arrays.asList(coords));
    }

    @Override
    public int hashCode() {

        Arrays.sort(coords, new Comparator<Coord>() {
            @Override
            public int compare(Coord o1, Coord o2) {

                if (o1.getX() > o2.getX()) {
                    return 1;
                } else if (o1.getX() < o2.getX()) {
                    return -1;
                }
                if (o1.getY() > o2.getY()) {
                    return 1;
                } else if (o1.getY() < o2.getY()) {
                    return -1;
                }
                if (o1.getZ() > o2.getZ()) {
                    return 1;
                } else if (o1.getZ() < o2.getZ()) {
                    return -1;
                }
                return 0;
            }
        });


        return Arrays.hashCode(coords);
    }
}
