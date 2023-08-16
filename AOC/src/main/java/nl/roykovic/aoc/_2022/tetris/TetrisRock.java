package nl.roykovic.aoc._2022.tetris;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TetrisRock {
    private final TetrisRockType type;
    private Coord coord;

    private final Coord[] occupiedCoords;

    public TetrisRock(TetrisRockType type, Coord coord) {
        this.type = type;
        this.coord = coord;
        occupiedCoords = new Coord[type.getCoords().length];

        for(int i =0 ; i< type.getCoords().length; i++){
            Coord typeCoord = type.getCoords()[i];
            occupiedCoords[i] = new Coord(typeCoord.getX(), typeCoord.getY());
        }

        for(Coord occupiedCoord : occupiedCoords){
            occupiedCoord.changeOrigin(coord);
        }

    }


    public boolean move(Direction direction, HashSet<Coord> coords){

        boolean moved = direction.getAdditionalX() != 0 && (this.coord.getX() + direction.getAdditionalX() >= 0 && this.coord.getX() + direction.getAdditionalX() + type.getWidth() < 8);

        if (direction.getAdditionalY() != 0 && (this.coord.getY() + direction.getAdditionalY() + type.getHeight() <= 0)){
            moved = true;
        }

        if(moved){
            Coord newOrigin = new Coord(direction.getAdditionalX(), direction.getAdditionalY());
            newOrigin.changeOrigin(coord);


            boolean collides = false;

            for(Coord occupiedCoord : occupiedCoords){
                occupiedCoord.move(direction);
            }


            for(Coord c: this.occupiedCoords){
                if (coords.contains(c)) {
                    collides = true;
                    break;
                }
            }

            if(collides) {
                for(Coord occupiedCoord : occupiedCoords){
                    occupiedCoord.move(Direction.OPPOSITE.get(direction));
                }
                return false;
            }

            this.coord = newOrigin;
        }

        return moved;
    }
    public boolean collidesWith(TetrisRock otherRock){
        for(Coord coord : occupiedCoords){
            if(Arrays.asList(otherRock.occupiedCoords).contains(coord)){
                return true;
            }
        }
        return false;
    }

    public TetrisRockType getType() {
        return type;
    }

    public Coord getCoord() {
        return coord;
    }

    public Coord[] getOccupiedCoords() {
        return occupiedCoords;
    }
}
