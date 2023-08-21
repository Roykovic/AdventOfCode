package nl.roykovic.aoc._2015.day12_jsonnumbers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import nl.roykovic.aoc._2015.day5_naughtystrings.NaughtyOrNiceFactory;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class JsonNumbersFactory {
    public int generateFromFile(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        ObjectMapper mapper = new ObjectMapper();
        var obj = mapper.readValue(line, JsonNode.class);
        var values = flatmapJsonNode(obj);
        return values.stream().mapToInt(Integer::intValue).sum();
    }

    private static List<Integer> flatmapJsonNode(JsonNode node){
        List<Integer> values = new ArrayList<>();
        if(node.isArray()){
            ArrayNode arrayNode = (ArrayNode) node;
            for (int i = 0; i < arrayNode.size(); i++) {
                values.addAll(flatmapJsonNode(arrayNode.get(i)));
            }
        }
        else if(node.isObject()){
            ObjectNode objectNode = (ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();

            while (iter.hasNext()) {
                Map.Entry<String, JsonNode> entry = iter.next();
                values.addAll(flatmapJsonNode(entry.getValue()));
            }
        }
        else if(node.isValueNode() && node.isNumber()){
            values.add(node.asInt());
        }
        return values;
    }
}
