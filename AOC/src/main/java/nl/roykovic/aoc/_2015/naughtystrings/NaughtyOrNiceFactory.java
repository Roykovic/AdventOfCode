package nl.roykovic.aoc._2015.naughtystrings;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NaughtyOrNiceFactory {
    public Map<String, Boolean> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines().collect(Collectors.toMap(it -> it, NaughtyOrNiceFactory::isNice));
    }

    public static boolean isNice(String input){

        if(Stream.of("ab", "cd", "pq", "xy").anyMatch(input::contains)){
            return false;
        }

        Pattern p = Pattern.compile("[aeiou]");
        Matcher m = p.matcher(input);
        if(!(m.results().count() >= 3)){
            return false;
        }

        Pattern doubleLettersPattern = Pattern.compile("(.)\\1+");
        Matcher doubleLettersMatcher = doubleLettersPattern.matcher(input);
        return doubleLettersMatcher.find();
    }
}
