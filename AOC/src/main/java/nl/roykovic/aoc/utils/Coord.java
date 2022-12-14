package nl.roykovic.aoc.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class Coord{
    private int x;
    private int y;

    public Coord() {
    }

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord(String coordinates) {
        String[] coord = coordinates.split(",");
        this.x = NumberUtils.toInt(coord[0]);
        this.y = NumberUtils.toInt(coord[1]);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean same = false;

        if (obj != null && obj instanceof Coord)
        {
            same = this.x == ((Coord) obj).getX() && this.y == ((Coord) obj).getY();
        }

        return same;
    }
}
