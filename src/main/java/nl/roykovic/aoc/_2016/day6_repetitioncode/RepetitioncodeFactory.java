package nl.roykovic.aoc._2016.day6_repetitioncode;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class RepetitioncodeFactory {
    public String generate(Stream<String> input) {

        char[][] input2dArray = input.map(String::toCharArray).toArray(char[][]::new);

        String[] columns = new String[input2dArray[0].length];

        for(int x = 0; x< input2dArray[0].length; x++){
            StringBuilder column = new StringBuilder();
            for (char[] chars : input2dArray) {
                column.append(chars[x]);
            }
            columns[x] = column.toString();
        }

        return Arrays.stream(columns).map(RepetitioncodeFactory::getMax).map(String::valueOf).collect(Collectors.joining());
    }

    public String generateLeastFavorite(Stream<String> input) {

        char[][] input2dArray = input.map(String::toCharArray).toArray(char[][]::new);

        String[] columns = new String[input2dArray[0].length];

        for(int x = 0; x< input2dArray[0].length; x++){
            StringBuilder column = new StringBuilder();
            for (char[] chars : input2dArray) {
                column.append(chars[x]);
            }
            columns[x] = column.toString();
        }

        return Arrays.stream(columns).map(RepetitioncodeFactory::getMin).map(String::valueOf).collect(Collectors.joining());
    }

    private static char getMin(String s) {
        Set<Character> uniqueChars = new HashSet<>();
        for(char c : s.toCharArray()){
            if(c != '-') {
                uniqueChars.add(c);
            }
        }
        char foundChar = ' ';
        int matches = Integer.MAX_VALUE;

        for(Character c : uniqueChars){
            int curMatches = StringUtils.countMatches(s, c);
            if(curMatches < matches){
                foundChar = c;
                matches =curMatches;
            }
        }

        return foundChar;
    }

    private static char getMax(String s) {
        Set<Character> uniqueChars = new HashSet<>();
        for(char c : s.toCharArray()){
            if(c != '-') {
                uniqueChars.add(c);
            }
        }
        char foundChar = ' ';
        int matches = 0;

        for(Character c : uniqueChars){
            int curMatches = StringUtils.countMatches(s, c);
            if(curMatches > matches){
                foundChar = c;
                matches =curMatches;
            }
        }

        return foundChar;
    }
}
