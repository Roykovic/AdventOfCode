package nl.roykovic.aoc.distresssignal;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacketFactoryTest {



    @Test
    void testExamplesRightOrder() throws IOException {
        File input = new File("src/test/resources/PacketTestInput.txt");
        LinkedHashMap<ArrayList<Object>, ArrayList<Object>> dataMap = new PacketFactory().generateFromFile(input);

        List<Integer> result = new ArrayList<>();
        for(Map.Entry<ArrayList<Object>, ArrayList<Object>> entry : dataMap.entrySet()){
            result.add(PacketUtils.compare(entry.getKey(), entry.getValue()));
        }

        int sum = IntStream.range(0, result.size())
                .filter(it -> result.get(it) == 1)
                .map(it -> it + 1)
                .sum();

        assertEquals(13,sum);
    }

    @Test
    void testActualRightOrder() throws IOException {
        File input = new ClassPathResource("PacketInput.txt").getFile();
        LinkedHashMap<ArrayList<Object>, ArrayList<Object>> dataMap = new PacketFactory().generateFromFile(input);

        List<Integer> result = new ArrayList<>();
        for(Map.Entry<ArrayList<Object>, ArrayList<Object>> entry : dataMap.entrySet()){
            result.add(PacketUtils.compare(entry.getKey(), entry.getValue()));
        }

        int sum = IntStream.range(0, result.size())
                .filter(it -> result.get(it) == 1)
                .map(it -> it + 1)
                .sum();

        assertEquals(6568,sum);
    }

    @Test
    void testExamplesDecoderKey() throws IOException {

        ArrayList<Object> dividerStartPacket = new ArrayList<>(new ArrayList<Object>(List.of(2)));
        ArrayList<Object> dividerEndPacket = new ArrayList<>(new ArrayList<Object>(List.of(6)));

        File input = new File("src/test/resources/PacketTestInput.txt");
        LinkedHashMap<ArrayList<Object>, ArrayList<Object>> dataMap = new PacketFactory().generateFromFile(input);

        List<ArrayList<Object>> dataList = new ArrayList<>(dataMap.keySet());
        dataList.addAll(dataMap.values());
        dataList.add(dividerStartPacket);
        dataList.add(dividerEndPacket);

        dataList.sort(new PacketComparator());
        Collections.reverse(dataList);

        int dividerStartPacketIndex = dataList.indexOf(dividerStartPacket) +1;
        int dividerEndPacketIndex = dataList.indexOf(dividerEndPacket) +1;

        assertEquals(140, dividerStartPacketIndex * dividerEndPacketIndex);
    }

    @Test
    void testActualDecoderKey() throws IOException {

        ArrayList<Object> dividerStartPacket = new ArrayList<>(new ArrayList<Object>(List.of(2)));
        ArrayList<Object> dividerEndPacket = new ArrayList<>(new ArrayList<Object>(List.of(6)));

        File input = new ClassPathResource("PacketInput.txt").getFile();
        LinkedHashMap<ArrayList<Object>, ArrayList<Object>> dataMap = new PacketFactory().generateFromFile(input);

        List<ArrayList<Object>> dataList = new ArrayList<>(dataMap.keySet());
        dataList.addAll(dataMap.values());
        dataList.add(dividerStartPacket);
        dataList.add(dividerEndPacket);

        dataList.sort(new PacketComparator());
        Collections.reverse(dataList);

        int dividerStartPacketIndex = dataList.indexOf(dividerStartPacket) +1;
        int dividerEndPacketIndex = dataList.indexOf(dividerEndPacket) +1;

        assertEquals(19493, dividerStartPacketIndex * dividerEndPacketIndex);
    }
}
