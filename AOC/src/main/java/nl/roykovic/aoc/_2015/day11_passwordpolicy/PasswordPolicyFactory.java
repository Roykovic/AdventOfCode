package nl.roykovic.aoc._2015.day11_passwordpolicy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PasswordPolicyFactory {



    public static String incrementPassword(String password){

        char[] characters = password.toCharArray();
        for(int i = characters.length -1; i >= 0; i--){
            char c = characters[i];
            if(c == 'z'){
                characters[i] = 'a';
            }
            else{
                characters[i]++;
                break;
            }
        }

        return String.valueOf(characters);
    }
    public static boolean isValid(String password){

        if(Stream.of("i", "o", "l").anyMatch(password::contains)){
            return false;
        }

        if(Stream.of("abc", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij","ijk", "jkl", "klm", "lmn", "mno", "nop", "opq", "pqr", "qrs", "rst", "stu", "tuv", "uvw", "vwx", "wxy", "xyz").noneMatch(password::contains)){
            return false;
        }

        Pattern doubleLettersPattern = Pattern.compile("(.)\\1+");
        Matcher doubleLettersMatcher = doubleLettersPattern.matcher(password);
        if(!(doubleLettersMatcher.results().count() >= 2)){
            return false;
        }

        return true;
    }
}
