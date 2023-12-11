package nl.roykovic.aoc._2023.day11_galaxies;

import nl.roykovic.aoc.utils.Coord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GalaxyFactory {
    public long generate(List<String> input, int growth){

        List<Coord> galaxies = new ArrayList<>();
        for(int y = 0; y< input.size(); y++){
            for(int x =0; x< input.get(y).length(); x++){
                if(input.get(y).charAt(x) == '#'){
                    galaxies.add(new Coord(x,y));
                }
            }
        }

        List<Integer> expandedRows = IntStream.range(0, input.size())
                .filter(i -> !input.get(i).contains("#"))
                .boxed()
                .toList();

        List<Integer> expandedColumns = getExpandedColumns(input);



        expandedRows.stream()
                .map(
                        it -> galaxies.stream().filter(g -> g.getY() > it)
                                .toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((key, value) -> key.setY(key.getY() + (value * (growth - 1))));

        expandedColumns.stream()
                .map(
                        it -> galaxies.stream().filter(g -> g.getX() > it)
                                .toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((key, value) -> key.setX(key.getX() + (value * (growth - 1))));

        long sum = 0;

        for(Coord c : galaxies){
            sum += galaxies.stream().filter(it -> !it.equals(c)).mapToLong(c::manhattanDistance).sum();
        }

        return sum/2;
    }

    private List<Integer> getExpandedColumns(List<String> input){
        List<Integer> expandedColumns = new ArrayList<>();
        for(int i =0 ; i<input.get(0).length(); i++){
            boolean containsGalaxies = false;
            for(String s : input){
                if (s.charAt(i) == '#') {
                    containsGalaxies = true;
                    break;
                }
            }
            if(!containsGalaxies){
                expandedColumns.add(i);
            }
        }
        return expandedColumns;
    }
}
