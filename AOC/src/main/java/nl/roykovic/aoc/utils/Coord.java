package nl.roykovic.aoc.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() +  ")";
    }


}
