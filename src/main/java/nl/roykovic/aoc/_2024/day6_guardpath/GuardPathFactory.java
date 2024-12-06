package nl.roykovic.aoc._2024.day6_guardpath;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
public class GuardPathFactory {
    public int generate(List<String> input) {

        Map<Coord, Boolean> visited = new HashMap<>();
        Coord curCoord = null;

        for(int j = 0; j< input.size(); j++){
            char[] sArr = input.get(j).toCharArray();
            for(int i = 0; i < sArr.length; i++){
                if(sArr[i] == '.'){
                    visited.put(new Coord(i,j), false);
                }
                else if(sArr[i] == '^'){
                    curCoord = new Coord(i,j);
                    visited.put(new Coord(i,j), true);
                }
            }
        }

        Direction curDir = Direction.U;

        while(curCoord != null){
            Coord newCoord = curCoord.moveAndGet(curDir);

            if(newCoord.equals(new Coord(7,9)) && curDir.equals(Direction.D)){
                System.out.printf("");
            }

            if(!visited.containsKey(newCoord)){
                if(newCoord.getY() < 0 || newCoord.getY() >= input.size() || newCoord.getX() < 0 || newCoord.getX() >= input.get(0).length()){
                    break;
                }
                else {
                    curDir = Direction.getFromViewingDirection(curDir, Direction.R);
                    newCoord = curCoord.moveAndGet(curDir);
                    if(newCoord.getY() < 0 || newCoord.getY() >= input.size() || newCoord.getX() < 0 || newCoord.getX() >= input.get(0).length()){
                        break;
                    }
                }
            }
            visited.put(newCoord,true);
            curCoord = newCoord;
        }

        return (int) visited.entrySet().stream().filter(Map.Entry::getValue).count();
    }
}
