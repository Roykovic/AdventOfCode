package nl.roykovic.aoc._2016.day7_abba;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
public class AbbaFactory {
    public int countTLS(Stream<String> input) {
        return (int)input.map(this::supportsTLS).filter(it -> it).count();
    }

    public int countSSL(Stream<String> input){
        return (int)input.map(this::supportsSSL).filter(it -> it).count();
    }

    public boolean supportsSSL(String input){
        var divided = divideStrings(input);

        var ABAs = divided.get("unbracketed").stream().map(this::getABA).filter(it -> !it.isEmpty()).flatMap(Collection::stream).toList();

        for(String s : divided.get("bracketed")){
            for(String aba : ABAs){

                String bab = String.valueOf(aba.charAt(1)) + aba.charAt(0) + aba.charAt(1);
                if(s.contains(bab)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean supportsTLS(String input){

        var divided = divideStrings(input);

        boolean bracketedABBA = divided.get("bracketed").stream().anyMatch(this::hasABBA);
        boolean unBracketedABBA = divided.get("unbracketed").stream().anyMatch(this::hasABBA);


        return !bracketedABBA && unBracketedABBA;
    }

    private List<String> getABA(String s){

        List<String> ABAs = new ArrayList<>();

        for(int i = 0; i +2 < s.length(); i++){
            char curChar = s.charAt(i);
            char nextChar = s.charAt(i+1);

            if(curChar != nextChar){
                if(curChar == s.charAt(i+2)){
                    ABAs.add( String.valueOf(curChar) +
                            nextChar +
                            curChar);
                }
            }
        }

        return ABAs;
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

    public Map<String, List<String>> divideStrings(String input){
        List<String> bracketed = List.of(StringUtils.substringsBetween(input, "[", "]"));;

        List<String> unBracketed = new ArrayList<>();

        for(String bracketedString : bracketed){
            unBracketed.add(input.substring(0, input.indexOf('[')));
            input = input.substring(Integer.min(input.indexOf(']')+1, input.length()-1));
        }

        unBracketed.add(input.substring(input.indexOf(']') +1));

        return Map.of("bracketed", bracketed, "unbracketed", unBracketed);
    }
}
