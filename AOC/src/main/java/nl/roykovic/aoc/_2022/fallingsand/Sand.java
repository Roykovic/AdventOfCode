package nl.roykovic.aoc._2022.fallingsand;

import nl.roykovic.aoc.utils.Coord;
import java.util.Map;
import java.util.Objects;

public class Sand implements IParticle{
    private Coord coord;

    public Sand(Coord coord) {
        this.coord = coord;
    }

    public boolean move( Map<Coord, IParticle> particleMap, Long floorY){

        Coord down = new Coord(this.coord.getX(), this.coord.getY() +1);
        Coord downLeft = new Coord(this.coord.getX() - 1, this.coord.getY()+1);
        Coord downRight = new Coord(this.coord.getX() + 1, this.coord.getY() +1);

        if(Objects.equals(down.getY(), floorY)){
            return false;
        }

        if(particleMap.get(down) == null){
            particleMap.remove(coord);
            setCoord(down);
            particleMap.put(coord, this);
            return true;
        }

        else if(particleMap.get(downLeft) == null){
            particleMap.remove(coord);
            setCoord(downLeft);
            particleMap.put(coord, this);
            return true;
        }

        else if(particleMap.get(downRight) == null){
            particleMap.remove(coord);
            setCoord(downRight);
            particleMap.put(coord, this);
            return true;
        }
        else{
            return false;
        }
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public Coord coord() {
        return coord;
    }
}
