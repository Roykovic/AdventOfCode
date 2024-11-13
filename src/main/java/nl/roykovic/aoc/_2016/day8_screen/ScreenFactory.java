package nl.roykovic.aoc._2016.day8_screen;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
public class ScreenFactory {

    boolean[][] screen = new boolean[6][50];
    public int generate(Stream<String> input) {
        var hans = input.peek(it -> {
            if(it.contains("rect")){
                int A = Integer.parseInt(StringUtils.substringBetween(it, "rect ", "x"));
                int B = Integer.parseInt(StringUtils.substringAfter(it,  "x"));

                rect(A,B);
            }
            else if(it.contains("rotate row")){
                int A = Integer.parseInt(StringUtils.substringBetween(it, "y=", " by"));
                int B = Integer.parseInt(StringUtils.substringAfter(it,  "by "));

                rotateRow(A, B);
            }
            else if(it.contains("rotate col")){
                int A = Integer.parseInt(StringUtils.substringBetween(it, "x=", " by"));
                int B = Integer.parseInt(StringUtils.substringAfter(it,  "by "));

                rotateCol(A, B);
            }
        })
                .toList();

        return (int) Arrays.stream(screen)
                .map(ArrayUtils::toObject)
                .flatMap(Arrays::stream)
                .filter(it -> it)
                .count();
    }

    public void rect(int A, int B){
        for(int x = 0; x < A; x++){
            for(int y = 0; y< B; y++){
                screen[y][x] = true;
            }
        }
    }

    public void rotateRow(int row, int shift){
        Boolean[] oldRow = ArrayUtils.toObject(screen[row]);
        Collections.rotate(Arrays.asList(oldRow), shift);

        screen[row] = ArrayUtils.toPrimitive(oldRow);
    }

    public void rotateCol(int col, int shift){

        Boolean[] oldCol = new Boolean[screen.length];

        for(int y = 0; y< screen.length; y++){
            oldCol[y] = screen[y][col];
        }

        Collections.rotate(Arrays.asList(oldCol), shift);

        for(int y = 0; y< screen.length; y++){
            screen[y][col] = oldCol[y];
        }
    }

    public void print(){
        for (boolean[] x : screen)
        {
            for (boolean y : x)
            {
                System.out.print(y?"#":".");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
