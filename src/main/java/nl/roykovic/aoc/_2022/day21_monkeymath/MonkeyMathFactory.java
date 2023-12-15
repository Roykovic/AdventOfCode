package nl.roykovic.aoc._2022.day21_monkeymath;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonkeyMathFactory {
    public Map<String, String> generateFromFile(Stream<String> lines){
        return lines.map(line ->
                        Map.entry(
                                StringUtils.substringBefore(line, ":"),
                                StringUtils.substringAfter(line, ":")))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
