package nl.roykovic.aoc._2023.day10_pipes;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.Arrays;

public class Pipe{
    private final char pipeChar;
    private Coord[] neighbours;
    private final Coord coord;

    private Long stepsFromStart;

    public Pipe(char pipeChar, Coord coord) {
        this.pipeChar = pipeChar;
        this.coord = coord;
        this.neighbours = new Coord[2];
        this.stepsFromStart = Long.MAX_VALUE;
        this.setNeighbours();
    }

    public void setNeighbours(Coord[] neighbours) {
        this.neighbours = neighbours;
    }

    private void setNeighbours(){
        switch (pipeChar){
            case '|' -> {neighbours[0]=coord.moveAndGet(Direction.U); neighbours[1]=coord.moveAndGet(Direction.D);}
            case '-' -> {neighbours[0]=coord.moveAndGet(Direction.L); neighbours[1]=coord.moveAndGet(Direction.R);}
            case 'L' -> {neighbours[0]=coord.moveAndGet(Direction.U); neighbours[1]=coord.moveAndGet(Direction.R);}
            case 'J' -> {neighbours[0]=coord.moveAndGet(Direction.U); neighbours[1]=coord.moveAndGet(Direction.L);}
            case '7' -> {neighbours[0]=coord.moveAndGet(Direction.D); neighbours[1]=coord.moveAndGet(Direction.L);}
            case 'F' -> {neighbours[0]=coord.moveAndGet(Direction.D); neighbours[1]=coord.moveAndGet(Direction.R);}
        }
    }

    public char getPipeChar() {
        return pipeChar;
    }

    public char getPipeCharStylized(){
       return switch (pipeChar){
            case '|' -> {yield '│';}
            case '-' -> {yield '─';}
            case 'L' -> {yield '└';}
            case 'J' -> {yield '┘';}
            case '7' -> {yield '┐';}
            case 'F' -> {yield '┌';}
           default -> {yield pipeChar;}
       };
    }

    public Long getStepsFromStart() {
        return stepsFromStart;
    }

    public void setStepsFromStart(Long stepsFromStart) {
        this.stepsFromStart = Long.min(stepsFromStart, this.stepsFromStart);
    }

    public Coord[] getNeighbours() {
        return neighbours;
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "pipeChar=" + pipeChar +
                ", neighbours=" + Arrays.toString(neighbours) +
                ", coord=" + coord +
                ", stepsFromStart=" + stepsFromStart +
                '}';
    }
}
