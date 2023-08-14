package nl.roykovic.aoc._2022.tetris;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class TetrisFactory {
    public Long generateFromFile(File file, long rounds) throws FileNotFoundException {

        List<TetrisRockType> pieces = List.of(TetrisRockType.LINE, TetrisRockType.PLUS, TetrisRockType.L, TetrisRockType.COLUMN, TetrisRockType.SQUARE);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        char[] instructions = reader.lines().findFirst().orElseThrow(IllegalArgumentException::new).toCharArray();

        long highestBlock = 0;

        HashSet<Coord> coords = new HashSet<>();

        int rockType = 0;
        int instructionPos = 0;

        for(int i = 0; i<rounds; i++){
            TetrisRock rock = new TetrisRock(pieces.get(rockType), new Coord(2L,(highestBlock - 3) - pieces.get(rockType).getHeight()));

            boolean hitBottom = false;

            while(!hitBottom){

                rock.move(directionFromChar(instructions[instructionPos]), coords);
                hitBottom = !rock.move(Direction.D, coords);

                if(instructionPos == instructions.length -1){
                    instructionPos =0;
                }
                else{
                    instructionPos++;
                }
            }

            coords.addAll(Arrays.asList(rock.getOccupiedCoords()));

            if(rockType == pieces.size() -1){
                rockType = 0;
            }
            else{
                rockType++;
            }

           highestBlock = Math.min(highestBlock, rock.getCoord().getY());
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
}
