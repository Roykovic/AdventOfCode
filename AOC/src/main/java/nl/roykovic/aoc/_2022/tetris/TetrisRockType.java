package nl.roykovic.aoc._2022.tetris;

import nl.roykovic.aoc.utils.Coord;

public enum TetrisRockType {
    LINE(1, 4,new Coord(0,0),new Coord(1,0),new Coord(2,0),new Coord(3,0)),
    PLUS(3, 3, new Coord(1,0),new Coord(0,1),new Coord(1,1),new Coord(2,1), new Coord(1,2)),
    L(3, 3,new Coord(2,0), new Coord(2,1), new Coord(0,2), new Coord(1,2), new Coord(2,2)),
    COLUMN(4, 1, new Coord(0,0), new Coord(0,1), new Coord(0,2), new Coord(0,3)),
    SQUARE(2,2,new Coord(0,0), new Coord(0,1), new Coord(1,0), new Coord(1,1));

    private final Coord[] coords;

    private final int height;
    private final int width;

    TetrisRockType(int height, int width, Coord ... coords) {
        this.height = height;
        this.width = width;
        this.coords = coords;
    }

    public Coord[] getCoords() {
        return coords;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
