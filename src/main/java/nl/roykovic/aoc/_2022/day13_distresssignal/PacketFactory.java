package nl.roykovic.aoc._2022.day13_distresssignal;

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
    public LinkedHashMap<ArrayList<Object>, ArrayList<Object>> generateFromFile(List<String> lines) throws JsonProcessingException {

        LinkedHashMap<ArrayList<Object>, ArrayList<Object>> dataMap = new LinkedHashMap<>();

        ArrayList<Object> left = null;

        for(String line: lines){

            if(!StringUtils.isBlank(line)) {
                ObjectMapper mapper = new ObjectMapper();
                ArrayList<Object> packetData = mapper.readValue(line, new TypeReference<>() {});

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
