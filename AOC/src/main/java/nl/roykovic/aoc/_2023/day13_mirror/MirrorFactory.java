package nl.roykovic.aoc._2023.day13_mirror;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class MirrorFactory {

    private final int smudges;
    public MirrorFactory(int smudges) {
        this.smudges = smudges;
    }

    public long generate(String input) {
        return Arrays
                .stream(input.split("\\r\\n[\\r\\n]+"))
                .map(it -> it.split("\\r\\n"))
                .map(it -> Map.entry(getHorizontalLine(it), getVerticalLine(it)))
                .mapToLong(it -> it.getValue() + (100*it.getKey()))
                .sum();
    }

    private long getHorizontalLine(String[] input){
        long horizontalLine = 0L;

        for(long i = 1; i < input.length; i++){
            String currentRow = input[(int) i];
            String previousRow = input[(int) (i-1)];

            if((currentRow.equals(previousRow) ||
                    StringUtils.getLevenshteinDistance(currentRow, previousRow) ==1) &&
                    checkSymmetryHorizontal(input, (int) i)){
                horizontalLine = i;
                break;
            }
        }
        return horizontalLine;
    }

    private long getVerticalLine(String[] input){
        long verticalLine = 0L;

        String previousString = "";

        for(int i =0 ; i<input[0].length(); i++){

            StringBuilder currentString = new StringBuilder();
            for(String s : input){
                currentString.append(s.charAt(i));
            }
            if(StringUtils.isNotBlank(previousString)){
                if((currentString.toString().equals(previousString) ||
                        StringUtils.getLevenshteinDistance(currentString, previousString) ==1) &&
                                checkSymmetryVertical(input, i)){
                    verticalLine = i;
                    break;
                }
            }
            previousString = currentString.toString();
        }
        return verticalLine;
    }

    private boolean checkSymmetryHorizontal(String[] input, int index){

        int up = index-1;
        int down = index;

        int smudges = 0;

        while(up >= 0 && down < input.length){
            String topString = input[up];
            String bottomString = input[down];

            if(!Objects.equals(topString, bottomString)){
                smudges += StringUtils.getLevenshteinDistance(topString, bottomString);
            }
            up--;
            down++;
        }

        return smudges == this.smudges;
    }

    private boolean checkSymmetryVertical(String[] input, int index){

        int left = index-1;
        int right = index;

        int smudges = 0;

        while(left >= 0 && right < input[0].length()){
            StringBuilder leftString = new StringBuilder();
            StringBuilder righString = new StringBuilder();

            for(String s : input){
                leftString.append(s.charAt(left));
                righString.append(s.charAt(right));
            }

            if(leftString.compareTo(righString) != 0){
                smudges += StringUtils.getLevenshteinDistance(leftString, righString);
            }
            left--;
            right++;
        }


        return smudges == this.smudges;
    }
}
