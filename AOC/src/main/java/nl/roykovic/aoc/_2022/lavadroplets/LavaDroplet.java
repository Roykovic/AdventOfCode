package nl.roykovic.aoc._2022.lavadroplets;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import nl.roykovic.aoc.utils.Face;

import java.util.List;

public class LavaDroplet {
    private final Coord origin;
    private List<LavaDroplet> neighbours;

    private Face[] faces = new Face[6];

    public LavaDroplet(Coord coord) {
        this.origin = coord;
        faces[0] = new Face(
                origin,
                new Coord(origin.getX() +1, origin.getY(), origin.getZ()),
                new Coord(origin.getX(), origin.getY(), origin.getZ()+1),
                new Coord(origin.getX() +1, coord.getY(), origin.getZ()+1));
        faces[1] = new Face(
                origin,
                new Coord(origin.getX(), origin.getY()-1, origin.getZ()),
                new Coord(origin.getX(), origin.getY(), origin.getZ()+1),
                new Coord(origin.getX(), coord.getY() -1, origin.getZ()+1));

        faces[2] = new Face(
                new Coord(origin.getX(), origin.getY()-1, origin.getZ()),
                new Coord(origin.getX(), origin.getY()-1, origin.getZ()+1),
                new Coord(origin.getX()+1, origin.getY()-1, origin.getZ()+1),
                new Coord(origin.getX()+1, coord.getY() -1, origin.getZ()));
        faces[3] = new Face(
                new Coord(origin.getX()+1, origin.getY(), origin.getZ()),
                new Coord(origin.getX()+1, origin.getY()-1, origin.getZ()+1),
                new Coord(origin.getX()+1, origin.getY(), origin.getZ()+1),
                new Coord(origin.getX()+1, coord.getY() -1, origin.getZ()));
        faces[4] = new Face(
                origin,
                new Coord(origin.getX() +1, origin.getY(), origin.getZ()),
                new Coord(origin.getX(), origin.getY()-1, origin.getZ()),
                new Coord(origin.getX() +1, coord.getY()-1, origin.getZ()));
        faces[5] = new Face(
                new Coord(origin.getX(), origin.getY(), origin.getZ()+1),
                new Coord(origin.getX() +1, origin.getY(), origin.getZ()+1),
                new Coord(origin.getX(), origin.getY()-1, origin.getZ()+1),
                new Coord(origin.getX() +1, coord.getY()-1, origin.getZ()+1));
    }

    public Coord getCoord() {
        return origin;
    }

    public Face[] getFaces() {
        return faces;
    }

    public List<LavaDroplet> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<LavaDroplet> neighbours) {
        this.neighbours = neighbours;
    }
}
