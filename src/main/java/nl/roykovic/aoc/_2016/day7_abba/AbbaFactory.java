package nl.roykovic.aoc._2016.day7_abba;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class AbbaFactory {
    public int generate(Stream<String> input) {
        return (int)input.map(this::supportsTLS).filter(it -> it).count();
    }

    public boolean supportsTLS(String input){
        List<String> bracketed = List.of(StringUtils.substringsBetween(input, "[", "]"));;

        List<String> unBracketed = new ArrayList<>();

        for(String bracketedString : bracketed){
            unBracketed.add(input.substring(0, input.indexOf('[')));
            input = input.substring(input.indexOf(']'));
        }

        unBracketed.add(input.substring(input.indexOf(']') +1));

        boolean bracketedABBA = bracketed.stream().anyMatch(this::hasABBA);
        boolean unBracketedABBA = unBracketed.stream().anyMatch(this::hasABBA);


        return !bracketedABBA && unBracketedABBA;
    }

    public boolean hasABBA(String s){

        for(int i = 0; i +3 < s.length(); i++){
            char curChar = s.charAt(i);
            char nextChar = s.charAt(i+1);

            if(curChar != nextChar){
                if(curChar == s.charAt(i+3) && nextChar == s.charAt(i+2)){
                    return true;
                }
            }

        }

        return false;
    }
}
