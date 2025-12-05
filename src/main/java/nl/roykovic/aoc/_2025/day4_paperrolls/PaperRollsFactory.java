package nl.roykovic.aoc._2025.day4_paperrolls;

import nl.roykovic.aoc.utils.Coord;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
public class PaperRollsFactory {
    List<String> input;

    public long generate(List<String> input, boolean p2) {

        this.input = new ArrayList<>(input);
        long result = 0;
        result += countRemovable();
        while(p2 && countRemovable() > 0) {
            result += countRemovable();
        }

        return this.input.stream().mapToInt(it -> StringUtils.countMatches(it, 'X')).sum();
    }

    private int countRemovable(){
        List<Coord> coords = new ArrayList<>();

        for(int y = 0 ; y < input.size(); y++){
            char[] chars = input.get(y).toCharArray();
            for(int x = 0; x < chars.length; x++){
                if(chars[x] == '@'){
                    coords.add(new Coord(x,y));
                }
            }
        }

        int count = 0;

        for(Coord coord : coords){
            int neighbours = 0;

            for(Coord neighbour : coord.getNeighbours()){
                if(coords.contains(neighbour)){
                    neighbours++;
                }
            }
            if(neighbours < 4){
                String oldLine = input.get(Math.toIntExact(coord.getY()));
                StringBuilder newLine = new StringBuilder(oldLine);
                newLine.setCharAt(Math.toIntExact(coord.getX()), 'X');

                input.set(Math.toIntExact(coord.getY()), newLine.toString());

                count++;
            }
        }

        return count;
    }
}
