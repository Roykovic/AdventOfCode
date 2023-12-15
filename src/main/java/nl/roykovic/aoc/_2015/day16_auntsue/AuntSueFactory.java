package nl.roykovic.aoc._2015.day16_auntsue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class AuntSueFactory
{
    public String generateFromFile(File file, Map<String, String> knownAttributes) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        String name = "notfound";

        for(String line : lines) {

            String attributesString = StringUtils.substringAfter(line, ":").trim();

            boolean correctSue = true;
            for(String attribute: attributesString.split(",")){
                String[] parts = attribute.split(":");
                String attributeName = parts[0].trim();
                String attributeValue = parts[1].trim();

                if(!knownAttributes.containsKey(attributeName) || !matches(attributeValue, knownAttributes.get(attributeName))){
                    correctSue = false;
                    break;
                }
            }

            if(correctSue){
                name = StringUtils.substringBefore(line, ":");
                break;
            }
        }

        return name;
    }

    private boolean matches(String s1, String s2){
        if(s2.contains(">")){
            return NumberUtils.toInt(s1) > NumberUtils.toInt(s2.substring(1));
        }
        if(s2.contains("<")){
            return NumberUtils.toInt(s1) < NumberUtils.toInt(s2.substring(1));
        }
        return NumberUtils.toInt(s1) == NumberUtils.toInt(s2);
    }
}
