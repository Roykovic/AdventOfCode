package nl.roykovic.aoc._2023.day10_pipes;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.*;

public class PipesFactory {

    public Long generate(List<String> input){

        Map<Coord, Pipe> pipes = new HashMap<>();
        Pipe startPipe = null;

        for(int y = 0; y< input.size(); y++){
            String currString = input.get(y);
            for(int x = 0; x < currString.length(); x++){
                char currChar = currString.charAt(x);
                Coord coord = new Coord(x, y);
                Pipe pipe = new Pipe(currChar, coord);
                pipes.put(coord, pipe);
                if(currChar == 'S'){
                    startPipe = pipe;
                }
            }
        }

        assert startPipe != null;

        Pipe finalStartPipe = startPipe;
        Coord[] startPipeNeighbours =  pipes.values().stream().filter(it -> Arrays.asList(it.neighbours).contains(finalStartPipe.getCoord())).map(Pipe::getCoord).toArray(Coord[]::new);
        startPipe.setNeighbours(startPipeNeighbours);

        for(Coord currentCoord : startPipe.getNeighbours()) {

            Pipe currentPipe = pipes.get(currentCoord);
            Coord oldCoord = startPipe.getCoord();
            long steps = 1;
            while (currentPipe != startPipe) {
                currentPipe.setStepsFromStart(steps);
                Coord finalOldCoord = oldCoord;
                Pipe newPipe = Arrays.stream(currentPipe.getNeighbours()).filter(it -> !it.equals(finalOldCoord)).findFirst().map(pipes::get).orElseThrow();
                oldCoord = currentPipe.getCoord();
                currentPipe = newPipe;
                steps++;
            }
        }
        return pipes.values().stream().map(Pipe::getStepsFromStart).filter(it -> it != Long.MAX_VALUE).max(Comparator.comparingLong(it -> it)).orElseThrow();
    }

    private static class Pipe{
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
}
