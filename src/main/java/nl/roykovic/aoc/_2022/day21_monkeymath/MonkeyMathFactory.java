package nl.roykovic.aoc._2022.day21_monkeymath;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonkeyMathFactory {
    public Map<String, String> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        Map<String, String> monkeyMap = new HashMap<>();

        for(String line: lines){
            String name = StringUtils.substringBefore(line, ":");
            String operation = StringUtils.substringAfter(line, ":");

            monkeyMap.put(name, operation);
        }

        return monkeyMap;
    }
}
