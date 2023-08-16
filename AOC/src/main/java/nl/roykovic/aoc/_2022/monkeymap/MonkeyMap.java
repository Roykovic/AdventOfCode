package nl.roykovic.aoc._2022.monkeymap;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

public class MonkeyMap {
    private char[][] map;
    private String[] instructions;

    private int currentX = 0;
    private int currentY = 0;
    private int currentDirection = 0;
private int index;
    private final Direction[] directions = {Direction.R, Direction.D, Direction.L, Direction.U};

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

    public void move(){
        currentX = ArrayUtils.indexOf(getMap()[0], '.');
        for(String instruction: getInstructions()){
            if(NumberUtils.isCreatable(instruction)){
                int numberInstruction = NumberUtils.toInt(instruction);
                for(int i = 0; i < numberInstruction; i++){
                    Coord newCoord = calculateNextCoord(currentX, currentY, directions[currentDirection]);

                    int newX = Math.toIntExact(newCoord.getX());
                    int newY = Math.toIntExact(newCoord.getY());

                    if(newX != currentX || newY != currentY){
                        currentX = newX;
                        currentY = newY;
                    }
                }
            }
            else{
                if(Objects.equals(instruction, "R")){
                    if(currentDirection == directions.length -1){
                        currentDirection = 0;
                    }
                    else {
                        currentDirection++;
                    }
                }
                else{
                        if(currentDirection == 0){
                            currentDirection = directions.length-1;
                        }
                        else {
                            currentDirection--;
                        }
                    }
            }
        }
    }
    public Coord calculateNextCoord(int currentX, int currentY, Direction direction){
        int newX = currentX + direction.getAdditionalX();
        int newY = currentY + direction.getAdditionalY();

        boolean offMap = newX < 0 || newY < 0 || newX >= map[0].length || newY>= map.length || map[newY][newX] != '.' && map[newY][newX] != '#';

        if(newX > map[0].length || (offMap && direction.equals(Direction.R))){

            int firstStandableTile = ArrayUtils.indexOf(map[currentY], '.') == -1? Integer.MAX_VALUE: ArrayUtils.indexOf(map[currentY], '.');
            int firstWall = ArrayUtils.indexOf(map[currentY], '#') == -1? Integer.MAX_VALUE: ArrayUtils.indexOf(map[currentY], '#');

            newX = Math.min(firstStandableTile, firstWall);
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
            for(int y = map.length -1; y > 0; y--){
                if(map[y][currentX] == '.' || map[y][currentX] == '#'){
                    newY = y;
                    break;
                }
            }
        }

        if(map[newY][newX] == '#'){
            newX = currentX;
            newY = currentY;
        }
        index++;
        return new Coord(newX, newY);
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }
}
