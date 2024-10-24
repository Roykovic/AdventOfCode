package nl.roykovic.aoc._2023.day18_lagoon;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

public record CoordRange(Coord start, Coord end, Direction direction) {
    public Long getAmountOfCoords() {
        return Math.abs(end.getX() - start.getX() + end.getY() - start.getY());
    }


    public boolean collidesWith(CoordRange otherRange) {
        var z =  this.start.getZ() <= otherRange.start.getZ() && this.end.getZ() >= otherRange.end.getZ();
        var x = this.start.getX() <= otherRange.start.getX() && this.end.getX() >= otherRange.end.getX();
        var y = this.start.getY() <= otherRange.start.getY() && this.end.getY() >= otherRange.end.getY();


        return  z && x && y;

    }

    public boolean containsX(Long x){
        return this.start.getX() <=x && this.end.getX() >= x;
    }

    public void moveDown(){
        start.setZ(start.getZ()-1);
        end.setZ(end.getZ()-1);
    }

    public void moveUp(){
        start.setZ(start.getZ()+1);
        end.setZ(end.getZ()+1);
    }

    @Override
    public Coord start() {
        return start;
    }

    @Override
    public Coord end() {
        return end;
    }
}
