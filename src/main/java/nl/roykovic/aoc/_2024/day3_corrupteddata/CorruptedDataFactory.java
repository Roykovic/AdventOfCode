package nl.roykovic.aoc._2024.day3_corrupteddata;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class CorruptedDataFactory {
    public int generate(String input, boolean ignoreDoAndDont) {
        return getMatchesFromString(input, ignoreDoAndDont).stream().map(this::multiply).mapToInt(it -> it).sum();
    }

    private int multiply(String multiplication){
        int a = Integer.parseInt(StringUtils.substringBetween(multiplication,"mul(", ","));
        int b = Integer.parseInt(StringUtils.substringBetween(multiplication,",", ")"));

        return a * b;
    }

    public List<String> getMatchesFromString(String input, boolean ignoreDoAndDont){
        Pattern p = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");

        List<String> uncorruptedMatches = new ArrayList<>();

        if(!ignoreDoAndDont){
            input = Arrays.stream(input.split("do\\(\\)")).map(it -> StringUtils.substringBefore(it, "don't()")).collect(Collectors.joining());
        }

        Matcher m = p.matcher(input);
        while(m.find()){
            uncorruptedMatches.add(m.group());
        }

        return uncorruptedMatches;
    }
}
