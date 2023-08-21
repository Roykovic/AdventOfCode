package nl.roykovic.aoc._2015.day16_auntsue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AuntSueFactory
{
    public String generateFromFile(File file, Map<String, Integer> knownAttributes) throws FileNotFoundException {

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

                if(!knownAttributes.containsKey(attributeName) || knownAttributes.get(attributeName) != NumberUtils.toInt(attributeValue)){
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
}
