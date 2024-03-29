package nl.roykovic.aoc._2023.day10_pipes;

import nl.roykovic.aoc.utils.Coord;

import java.util.*;
import java.util.stream.Collectors;

public class PipesFactory {

    public Map<Coord, Pipe> generate(List<String> input) {

        Map<Coord, Pipe> pipes = new HashMap<>();
        Pipe startPipe = null;

        for (int y = 0; y < input.size(); y++) {
            String currString = input.get(y);
            for (int x = 0; x < currString.length(); x++) {
                char currChar = currString.charAt(x);
                Coord coord = new Coord(x, y);
                Pipe pipe = new Pipe(currChar, coord);
                pipes.put(coord, pipe);
                if (currChar == 'S') {
                    startPipe = pipe;
                }
            }
        }

        assert startPipe != null;

        startPipe.setStepsFromStart(0L);

        Pipe finalStartPipe = startPipe;
        Coord[] startPipeNeighbours = pipes.values().stream().filter(it -> Arrays.asList(it.getNeighbours()).contains(finalStartPipe.getCoord())).map(Pipe::getCoord).toArray(Coord[]::new);
        startPipe.setNeighbours(startPipeNeighbours);

        LinkedHashMap<Coord, Pipe> closedPipeLoop = new LinkedHashMap<>();

        Pipe currentPipe = pipes.get(startPipeNeighbours[0]);
        Coord oldCoord = startPipe.getCoord();
        closedPipeLoop.put(startPipe.getCoord(), startPipe);

        long steps = 1;
        while (currentPipe != startPipe) {
            //add the newly found neighbour to the loop
            closedPipeLoop.put(currentPipe.getCoord(), currentPipe);
            //set its stepsFromStart
            currentPipe.setStepsFromStart(steps);
            //Get the new neighbour (that is not our previous coord)
            Coord finalOldCoord = oldCoord;
            Pipe newPipe = Arrays.stream(currentPipe.getNeighbours()).filter(it -> !it.equals(finalOldCoord)).findFirst().map(pipes::get).orElseThrow();
            //Set variables accordingly
            oldCoord = currentPipe.getCoord();
            currentPipe = newPipe;
            //increase steps
            steps++;
        }
        return closedPipeLoop;
    }
}
