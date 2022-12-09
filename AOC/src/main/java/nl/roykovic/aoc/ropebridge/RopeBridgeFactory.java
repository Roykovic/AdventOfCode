package nl.roykovic.aoc.ropebridge;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class RopeBridgeFactory {
    public List<Map.Entry<Integer, Integer>> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        Head head = new Head(0,0);
        Tail tail = new Tail(0,0);

        for(String line: lines){
            String[] parts =line.split(" ");
            Direction direction = Direction.valueOf(parts[0]);
            int amount = NumberUtils.createInteger(parts[1]);

            for(int i = 0; i< amount; i++){
                head.move(direction);
                tail.moveToHead(head.x, head.y);
//                drawGrid(head, tail);
            }
        }

//        System.out.println(head.path);
//        System.out.println(tail.path);
        return tail.path;
    }

    private void drawGrid(Head head, Tail tail){
        for(int y = 0; y < 5; y++){
            for(int x = -0; x < 6; x++){
                if(head.getX() == x && head.getY() == y-4){
                    System.out.print("H");
                }
                else if(tail.getX() == x && tail.getY() == y-4){
                    System.out.print("T");
                }
                else if(x ==0 && y == 4){
                    System.out.print("s");
                }
                else{
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }
}
