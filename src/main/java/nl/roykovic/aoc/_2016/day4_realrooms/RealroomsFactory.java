package nl.roykovic.aoc._2016.day4_realrooms;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Stream;
public class RealroomsFactory {
    public List<Room> generate(Stream<String> input) {
      return input.map(it -> {

            int checksumIndex = it.indexOf('[');
            String checksum = it.substring(checksumIndex);

            String rest = it.substring(0,checksumIndex);

            String id = StringUtils.getDigits(rest);
            String name = rest.replaceAll("[^a-zA-Z]+", "");

            return new Room(Integer.parseInt(id), name, checksum);
        }).toList();
    }
}
