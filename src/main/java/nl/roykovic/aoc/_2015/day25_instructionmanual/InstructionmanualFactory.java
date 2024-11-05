package nl.roykovic.aoc._2015.day25_instructionmanual;

import java.util.stream.Stream;
public class InstructionmanualFactory {
    public Long generate(int ordinal) {

        Long lastVal = 20151125L;
        for(int i = 1; i < ordinal; i++){
            Long curVal = lastVal * 252533;
            lastVal = curVal % 33554393;
        }

        return lastVal;
    }


    public int findOrdinalByRowAndCol(int row, int col){
        int ordinalOfFirstInCol = (col+1) * col /2;

        int val = ordinalOfFirstInCol;
        for(int i = 2; i <= row; i++){
            val += col + i-2;
        }
        return val;

    }
}
