package nl.roykovic.aoc._2016.day18_safetiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class SafeTilesFactory {
    public int generate(String input, int rows) {

        List<String> field = new ArrayList<>(List.of(input));
        String previousRow = input;
        while(field.size() < rows){
            StringBuilder currentRow = new StringBuilder();
            for(int i = 0; i < previousRow.length(); i++){

                char left = i > 0? previousRow.charAt(i-1): '.';
                char center = previousRow.charAt(i);
                char right = i < previousRow.length() -1? previousRow.charAt(i+1): '.';

                currentRow.append(getChar(left, center, right));
            }
            field.add(currentRow.toString());
            previousRow = currentRow.toString();
        }

        return field.stream().map(it -> it.replace("^", "")).mapToInt(String::length).sum();
    }

    private char getChar(char left, char center, char right){
        if(left == '^' && center == '^' && right == '.'){
            return '^';
        }
        if(center == '^' && right == '^' && left == '.'){
            return '^';
        }
        if(center == '.' && right == '^' && left == '.'){
            return '^';
        }
        if(left == '^' && center == '.' && right == '.'){
            return '^';
        }
        return '.';
    }
}
