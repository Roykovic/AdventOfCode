package nl.roykovic.aoc._2023.day9_oasis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class OasisFactory {

    public List<OasisList> generate(Stream<String> input){

        return input.map(it -> Arrays.stream(it.split(" "))
                        .mapToLong(Long::parseLong)
                        .boxed()
                        .toList())
                .map(OasisList::new)
                .toList();
    }
}
