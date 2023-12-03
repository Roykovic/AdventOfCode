package nl.roykovic.aoc.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

@SuppressWarnings("unused")
public class Coord{
    private Long x;
    private Long y;
    private Long z;

    public Coord(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Coord(Long x, Long y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coord(int x, int y) {
        this.x = (long) x;
        this.y = (long) y;
    }

    public Coord(String coordinates) {
        String[] coord = coordinates.split(",");
        this.x = NumberUtils.toLong(coord[0]);
        this.y = NumberUtils.toLong(coord[1]);

        if(coord.length == 3){
            this.z = NumberUtils.toLong(coord[2]);
        }
    }

    public Coord(Coord coord){
        this.x = coord.getX();
        this.y = coord.getY();
        this.z = coord.getZ();
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public Long manhattanDistance(Coord other){
        return Math.abs(x - other.getX()) + Math.abs(y - other.getY());
    }

    public void changeOrigin(Coord origin){
        this.x = this.x + origin.x;
        this.y = this.y + origin.y;
    }

    public void move(Direction direction){
        this.x = this.x + direction.getAdditionalX();
        this.y = this.y + direction.getAdditionalY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return Objects.equals(x, coord.x) && Objects.equals(y, coord.y) && Objects.equals(z, coord.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {

        String out = "(" + getX() + ", " + getY();

        if(getZ() != null){
            out +=", " + getZ();
        }
        out  +=  ")";

        return out;
    }


}
