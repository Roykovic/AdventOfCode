package nl.roykovic.aoc._2015.day12_jsonnumbers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JsonNumbersFactory {
    public int generateFromFile(File file, String exclude) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        ObjectMapper mapper = new ObjectMapper();
        var obj = mapper.readValue(line, JsonNode.class);
        var values = flatmapJsonNode(obj, exclude);
        return values.stream().mapToInt(Integer::intValue).sum();
    }

    private static List<Integer> flatmapJsonNode(JsonNode node, String exclude){
        List<Integer> values = new ArrayList<>();
        if(node.isArray()){
            ArrayNode arrayNode = (ArrayNode) node;
            for (int i = 0; i < arrayNode.size(); i++) {
                values.addAll(flatmapJsonNode(arrayNode.get(i), exclude));
            }
        }
        else if(node.isObject() && node.get("red") == null){
            ObjectNode objectNode = (ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();

            while (iter.hasNext()) {
                Map.Entry<String, JsonNode> entry = iter.next();
                if( exclude != null && Objects.equals(entry.getValue().asText(), exclude)){
                    return Collections.emptyList();
                }
                values.addAll(flatmapJsonNode(entry.getValue(), exclude));
            }
        }
        else if(node.isValueNode() && node.isNumber()){
            values.add(node.asInt());
        }
        return values;
    }
}
