package nl.roykovic.aoc._2022.day3_rucksack;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RucksackFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "RucksackTestInput.txt,true,157",
            "RucksackInput.txt,false,7553",
    })
    void testPriority(String filename, boolean test, int expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        int sum =  new RucksackFactory().generateFromFile(input)
                .flatMap(rucksack -> Arrays.stream(rucksack.sharedItems()))
                .mapToInt(Rucksack::charToPrio)
                .sum();

        assertEquals(expected,sum);
    }

    @ParameterizedTest
    @CsvSource({
            "RucksackTestInput.txt,true,70",
            "RucksackInput.txt,false,2758",
    })
    void testThreeElvesExamplePriority(String filename, boolean test, int expected) throws IOException {
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        var list =  new RucksackFactory().generateFromFile(input).toList();

        Map<Integer, List<Rucksack>> groupsMap =
                list.stream().collect(Collectors.groupingBy(r -> (list.indexOf(r)) / 3));

        int sum = new ArrayList<>(groupsMap.values()).stream()
                .flatMap(group -> Arrays.stream(group.get(2).sharedItems(group.get(0).sharedItems(group.get(1)))))
                .map(Rucksack::charToPrio)
                .reduce(0, Integer::sum);

        assertEquals(expected, sum);
    }
}
