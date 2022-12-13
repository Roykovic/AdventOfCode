package nl.roykovic.aoc.distresssignal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PacketFactory {
    public LinkedHashMap<ArrayList, ArrayList> generateFromFile(File file) throws FileNotFoundException, JsonProcessingException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        LinkedHashMap<ArrayList, ArrayList> dataMap = new LinkedHashMap<>();

        ArrayList left = null;

        for(String line: lines){

            if(!StringUtils.isBlank(line)) {
                ObjectMapper mapper = new ObjectMapper();
                ArrayList packetData = mapper.readValue(line, new TypeReference<>() {});

                if(left == null){
                    left = packetData;
                }
                else{
                    dataMap.put(left, packetData);
                }
            }
            else{
                left = null;
            }
        }
        return dataMap;
    }
}
