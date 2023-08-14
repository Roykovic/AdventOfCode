package nl.roykovic.aoc._2022.tetris;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TetrisFactory {
    public Long generateFromFile(File file, long rounds) throws FileNotFoundException {

        List<TetrisRockType> pieces = List.of(TetrisRockType.LINE, TetrisRockType.PLUS, TetrisRockType.L, TetrisRockType.COLUMN, TetrisRockType.SQUARE);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        char[] instructions = reader.lines().findFirst().orElseThrow(IllegalArgumentException::new).toCharArray();

        long highestBlock = 0;

        List<TetrisRock> rocks = new ArrayList<>();

        int rockType = 0;
        int instructionPos = 0;

        for(int i = 0; i<rounds; i++){

            TetrisRock rock = new TetrisRock(pieces.get(rockType), new Coord(2L,(highestBlock - 3) - pieces.get(rockType).getHeight()));
            rocks.add(rock);

            boolean hitBottom = false;

            while(!hitBottom){

                rock.move(directionFromChar(instructions[instructionPos]), rocks);
                hitBottom = !rock.move(Direction.D, rocks);

                if(instructionPos == instructions.length -1){
                    instructionPos =0;
                }
                else{
                    instructionPos++;
                }
            }

            if(rockType == pieces.size() -1){
                rockType = 0;
            }
            else{
                rockType++;
            }

           highestBlock = Math.min(highestBlock, rock.getCoord().getY());
//            draw(rocks, highestBlock);
        }


        return Math.abs(highestBlock);
    }

    private Direction directionFromChar(char direction){
        switch(direction){
            case '<' -> {
                return Direction.L;
            }
            case '>' -> {
                return Direction.R;
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private void draw(List<TetrisRock> rocks, long lowestY){
        for(long y = -14; y < 0; y++){
            for(int x = 0; x <7; x++){
                boolean drawn = false;
                for(TetrisRock rock: rocks){
                    for(Coord coord : rock.getOccupiedCoords()){
                        if(coord.equals(new Coord((long) x, y))){
                            drawn = true;
                            System.out.print("#");
                        }
                    }
                }
                if(!drawn){
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private int getRepeatingRounds(int instructionsLength, int amountOfShapes){
        float repeatAfterRounds = instructionsLength/amountOfShapes;

        return (int) (repeatAfterRounds * amountOfShapes);
    }
}
