package nl.roykovic.aoc._2015.day8_memoryusage;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class MemoryUsageFactory {
    public Long generateFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        return getMemoryDifFromList(reader.lines().toList());
    }

    public Long generateFromFileOtherWay(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        return getMemoryDifOtherWay(reader.lines().toList());
    }

    public Long getMemoryDifFromList(List<String> lines){
        Long characterCount = 0L;
        Long memoryCount = 0L;

        for(String line : lines){
            characterCount += line.length();

            memoryCount += sanitize(line).length();
        }
        return characterCount - memoryCount;
    }

    public Long getMemoryDifOtherWay(List<String> lines){
        long characterCount = 0L;
        long memoryCount = 0L;

        for(String line : lines){
            characterCount += StringEscapeUtils.escapeJava(line).length() +2;

            memoryCount += line.length();
        }
        return characterCount - memoryCount;
    }


    private String sanitize(String dirtyString){

        //removing quotes
        dirtyString = dirtyString.substring(1, dirtyString.length()-1);

        //finding hexadecimal codes
        if(dirtyString.contains("\\x")){
            dirtyString = dirtyString.replaceAll("\\\\x[0-9a-fA-F][0-9a-fA-F]","X");
        }

        //finding escaped backslashes
        if(dirtyString.contains("\\\\")){
            dirtyString = dirtyString.replace("\\\\", "x");
        }

        //finding escaped quotes
        if(dirtyString.contains("\\\"")){
            dirtyString = dirtyString.replace("\\\"", "x");
        }

        //we just replace everything with x here, because we only care about length
        //replacing with the actual values will cause escaping problems.

        return dirtyString;
    }
}
