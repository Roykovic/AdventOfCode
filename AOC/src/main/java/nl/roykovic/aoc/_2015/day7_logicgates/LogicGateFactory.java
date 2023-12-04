package nl.roykovic.aoc._2015.day7_logicgates;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class LogicGateFactory {
    public Short generateFromFile(File file, String wireToKnow) throws FileNotFoundException {

        Map<String, String> wireMap = new HashMap<>();
        Map<String, Short> calculatedWireMap = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.lines().forEach(line -> {
            String[] parts = StringUtils.splitByWholeSeparator(line, " -> ");

            if(NumberUtils.isParsable(parts[0])){
                calculatedWireMap.put(parts[1], Short.valueOf(parts[0]));
            }
            else {
                wireMap.put(parts[1], parts[0]);
            }
        });

        while(!calculatedWireMap.containsKey(wireToKnow)) {

            Map<String, String> concurrentMap = new HashMap<>();
            calculatedWireMap.forEach((ck, cv) -> wireMap.forEach((k, v) -> {

                if(v.contains(ck)) {
                    v = v.replaceAll("^" + ck + " ", cv + " ");
                    v = v.replaceAll(" " + ck + "$", " " + cv);
                    v = v.replaceAll("^" + ck + "$", String.valueOf(cv));
                }

                if (v.equals(v.toUpperCase())) {
                    concurrentMap.put(k, v);
                } else {
                    wireMap.put(k, v);
                }
            }));

            concurrentMap.forEach((k,v) -> {
                wireMap.remove(k);
                calculatedWireMap.put(k, calculateBitOperation(v));
            });
        }

        return calculatedWireMap.get(wireToKnow);
    }

    private Short calculateBitOperation(String bitOperation){

        String leftValue = "";
        String rightValue = "";
        String operation = "";

        String[] parts = bitOperation.split(" ");

        if(parts.length == 1){
            return NumberUtils.toShort(parts[0]);
        }
        if(parts.length == 2){
            leftValue = parts[1];
            operation = parts[0];
        }
        else{
            leftValue = parts[0];
            operation = parts[1];
            rightValue = parts[2];
        }

        switch (operation){
            case "AND" -> {
                return (short) (NumberUtils.toShort(leftValue) & NumberUtils.toShort(rightValue));
            }
            case "OR" -> {
                return (short) (NumberUtils.toShort(leftValue) | NumberUtils.toShort(rightValue));
            }
            case "LSHIFT" -> {
                return (short) (NumberUtils.toShort(leftValue) << NumberUtils.toShort(rightValue));
            }
            case "RSHIFT" -> {
                return (short) (NumberUtils.toShort(leftValue) >> NumberUtils.toShort(rightValue));
            }
            case "NOT" -> {
                return (short) (~NumberUtils.toShort(leftValue));
            }
        }
        throw new RuntimeException();
    }
}
