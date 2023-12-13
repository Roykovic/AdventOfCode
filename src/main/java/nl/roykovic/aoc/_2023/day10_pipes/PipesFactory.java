package nl.roykovic.aoc._2023.day10_pipes;

import nl.roykovic.aoc.utils.Coord;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PipesFactory {

    public Map<Coord, Pipe> generate(List<String> input){

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

        startPipe.setStepsFromStart(0L);

        Pipe finalStartPipe = startPipe;
        Coord[] startPipeNeighbours =  pipes.values().stream().filter(it -> Arrays.asList(it.getNeighbours()).contains(finalStartPipe.getCoord())).map(Pipe::getCoord).toArray(Coord[]::new);
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
        return pipes.entrySet().stream().filter(it -> it.getValue().getStepsFromStart() != Long.MAX_VALUE).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
