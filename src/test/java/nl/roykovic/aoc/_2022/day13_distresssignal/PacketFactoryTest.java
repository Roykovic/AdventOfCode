package nl.roykovic.aoc._2022.day13_distresssignal;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacketFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "PacketTestInput.txt,true,13",
            "PacketInput.txt,false,6568"
    })
    void testRightOrder(String filename, boolean test, long expected) throws JsonProcessingException {
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        var dataMap = new PacketFactory().generateFromFile(input);

        List<Integer> result = new ArrayList<>();
        for(Map.Entry<ArrayList<Object>, ArrayList<Object>> entry : dataMap.entrySet()){
            result.add(PacketUtils.compare(entry.getKey(), entry.getValue()));
        }

        int sum = IntStream.range(0, result.size())
                .filter(it -> result.get(it) == 1)
                .map(it -> it + 1)
                .sum();

        assertEquals(expected,sum);
    }

    @ParameterizedTest
    @CsvSource({
            "PacketTestInput.txt,true,140",
            "PacketInput.txt,false,19493"
    })
    void testDecoderKey(String filename, boolean test, long expected) throws JsonProcessingException {

        ArrayList<Object> dividerStartPacket = new ArrayList<>(new ArrayList<Object>(List.of(2)));
        ArrayList<Object> dividerEndPacket = new ArrayList<>(new ArrayList<Object>(List.of(6)));

        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        LinkedHashMap<ArrayList<Object>, ArrayList<Object>> dataMap = new PacketFactory().generateFromFile(input);

        List<ArrayList<Object>> dataList = new ArrayList<>(dataMap.keySet());
        dataList.addAll(dataMap.values());
        dataList.add(dividerStartPacket);
        dataList.add(dividerEndPacket);

        dataList.sort(new PacketComparator());
        Collections.reverse(dataList);

        int dividerStartPacketIndex = dataList.indexOf(dividerStartPacket) +1;
        int dividerEndPacketIndex = dataList.indexOf(dividerEndPacket) +1;

        assertEquals(expected, (long) dividerStartPacketIndex * dividerEndPacketIndex);
    }
}
