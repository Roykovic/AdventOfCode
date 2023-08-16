package nl.roykovic.aoc._2022.monkeymap;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import org.apache.commons.lang3.ArrayUtils;

public class MonkeyMap {
    private char[][] map;
    private String[] instructions;

    public MonkeyMap(char[][] map, String[] instructions) {
        this.map = map;
        this.instructions = instructions;
    }

    public char[][] getMap() {
        return map;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public Coord calculateNextCoord(int currentX, int currentY, Direction direction){
        int newX = currentX + direction.getAdditionalX();
        int newY = currentY + direction.getAdditionalY();

        boolean offMap = newX <= 0 || newY <= 0 || map[newX][newY] != '.' && map[newX][newY] != '#';

        if(newX > map[0].length || (offMap && direction.equals(Direction.R))){
            newX = Math.min(ArrayUtils.indexOf(map[currentY], '.'), ArrayUtils.indexOf(map[currentY], '#'));
        }
        else if(newY > map.length || (offMap && direction.equals(Direction.D))){
            for(int y = 0; y < map.length; y++){
                if(map[y][currentX] == '.' || map[y][currentX] == '#'){
                    newY = y;
                    break;
                }
            }
        }
        else if(newX < 0 || (offMap && direction.equals(Direction.L))){
            newX = Math.max(ArrayUtils.lastIndexOf(map[currentY], '.'), ArrayUtils.lastIndexOf(map[currentY], '#'));
        }
        else if(newY < 0 || (offMap && direction.equals(Direction.U))){
            for(int y = map.length; y > 0; y--){
                if(map[y][currentX] == '.' || map[y][currentX] == '#'){
                    newY = y;
                    break;
                }
            }
        }
        return new Coord(newX, newY);
    }
}
