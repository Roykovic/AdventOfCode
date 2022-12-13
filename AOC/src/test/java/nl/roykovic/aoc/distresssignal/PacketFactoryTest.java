package nl.roykovic.aoc.distresssignal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.roykovic.aoc.rucksack.Rucksack;
import nl.roykovic.aoc.rucksack.RucksackFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PacketFactoryTest {



    @Test
    void testExamplesRightOrder() throws IOException {
        File input = new File("src/test/resources/PacketTestInput.txt");
        LinkedHashMap<ArrayList, ArrayList> dataMap = new PacketFactory().generateFromFile(input);

        List<Boolean> result = new ArrayList<>();
        for(Map.Entry<ArrayList, ArrayList> entry : dataMap.entrySet()){
            result.add(PacketUtils.compare(entry.getKey(), entry.getValue()));
        }

        int sum = IntStream.range(0, result.size())
                .filter(result::get)
                .map(it -> it + 1)
                .reduce(0, Integer::sum);

        assertEquals(13,sum);
    }

    @Test
    void testActualRightOrder() throws IOException {
        File input = new ClassPathResource("PacketInput.txt").getFile();
        LinkedHashMap<ArrayList, ArrayList> dataMap = new PacketFactory().generateFromFile(input);

        List<Boolean> result = new ArrayList<>();
        for(Map.Entry<ArrayList, ArrayList> entry : dataMap.entrySet()){
            result.add(PacketUtils.compare(entry.getKey(), entry.getValue()));
        }

        System.out.println(result.size());

        int sum = IntStream.range(0, result.size())
                .filter(result::get)
                .map(it -> it + 1)
                .reduce(0, Integer::sum);

        assertEquals(6568,sum);
    }
}
