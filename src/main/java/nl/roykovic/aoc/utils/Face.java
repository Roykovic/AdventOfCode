package nl.roykovic.aoc.utils;

import java.util.Arrays;
import java.util.HashSet;

public record Face(Coord... coords) {

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Face f)) {
            return false;
        }

        return new HashSet<>(Arrays.asList(f.coords())).containsAll(Arrays.asList(coords));
    }

    @Override
    public int hashCode() {

        Arrays.sort(coords, (o1, o2) -> {

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
        });


        return Arrays.hashCode(coords);
    }
}
