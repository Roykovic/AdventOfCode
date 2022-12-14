package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.utils.Coord;

import java.util.List;

public class Sand implements IParticle{
    private Coord coord;

    public Sand(Coord coord) {
        this.coord = coord;
    }

    public boolean move(List<IParticle> particles){

        Coord down = new Coord(this.coord.getX(), this.coord.getY() +1);
        Coord downLeft = new Coord(this.coord.getX() - 1, this.coord.getY()+1);
        Coord downRight = new Coord(this.coord.getX() + 1, this.coord.getY() +1);

        if(particles.stream().noneMatch(it -> it.getCoord().equals(down))){
            setCoord(down);
            return true;
        }

        else if(particles.stream().noneMatch(it -> it.getCoord().equals(downLeft))){
            setCoord(downLeft);
            return true;
        }

        else if(particles.stream().noneMatch(it -> it.getCoord().equals(downRight))){
            setCoord(downRight);
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
    public Coord getCoord() {
        return coord;
    }
}
