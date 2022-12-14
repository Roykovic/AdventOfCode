package nl.roykovic.aoc._2022.rucksack;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RucksackFactoryTest {
    @Test
    void testExamplesPriority() throws IOException {
        File input = new File("src/test/resources/2022/RucksackTestInput.txt");
        List<Rucksack> list = new RucksackFactory().generateFromFile(input);

        int sum = list.stream().flatMap(rucksack -> Arrays.stream(rucksack.sharedItems())).map(Rucksack::charToPrio).reduce(0, Integer::sum);

        assertEquals(157,sum);
    }

    @Test
    void testActualority() throws IOException {
        File input = new ClassPathResource("2022/RucksackInput.txt").getFile();
        List<Rucksack> list = new RucksackFactory().generateFromFile(input);

        int sum = list.stream().flatMap(rucksack -> Arrays.stream(rucksack.sharedItems())).map(Rucksack::charToPrio).reduce(0, Integer::sum);

        assertEquals(7553,sum);
    }

    @Test
    void testThreeElvesExamplePriority() throws IOException {
        File input = new File("src/test/resources/2022/RucksackTestInput.txt");
        List<Rucksack> list = new RucksackFactory().generateFromFile(input);

        Map<Integer, List<Rucksack>> groupsMap =
                list.stream().collect(Collectors.groupingBy(r -> (list.indexOf(r)) / 3));

        List<List<Rucksack>> groups = new ArrayList<>(groupsMap.values());

        int sum = groups.stream()
                .flatMap(group -> Arrays.stream(group.get(2).sharedItems(group.get(0).sharedItems(group.get(1)))))
                .map(Rucksack::charToPrio)
                .reduce(0, Integer::sum);

        assertEquals(70, sum);
    }

    @Test
    void testThreeElvesActualPriority() throws IOException {
        File input = new ClassPathResource("2022/RucksackInput.txt").getFile();
        List<Rucksack> list = new RucksackFactory().generateFromFile(input);

        Map<Integer, List<Rucksack>> groupsMap =
                list.stream().collect(Collectors.groupingBy(r -> (list.indexOf(r)) / 3));

        List<List<Rucksack>> groups = new ArrayList<>(groupsMap.values());

        int sum = groups.stream()
                .flatMap(group -> Arrays.stream(group.get(2).sharedItems(group.get(0).sharedItems(group.get(1)))))
                .map(Rucksack::charToPrio)
                .reduce(0, Integer::sum);

        assertEquals(2758, sum);
    }
}
