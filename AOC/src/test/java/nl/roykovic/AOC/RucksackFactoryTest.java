package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.Rucksack;
import nl.roykovic.AOC.domain.RucksackFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
public class RucksackFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("RucksackInput.txt").getFile();
        List<Rucksack> list = new RucksackFactory().generateFromFile(input);

       int sum = list.stream().flatMap(rucksack -> Arrays.stream(rucksack.sharedItems())).map(Rucksack::charToPrio).reduce(0, Integer::sum);

        System.out.println(sum);

    }

    @Test
    void testGroupFactory() throws IOException {
        File input = new ClassPathResource("RucksackInput.txt").getFile();
        List<Rucksack> list = new RucksackFactory().generateFromFile(input);

        Map<Integer, List<Rucksack>> groupsMap =
                list.stream().collect(Collectors.groupingBy(r -> (list.indexOf(r)) / 3));

        List<List<Rucksack>> groups = new ArrayList<>(groupsMap.values());

        int sum = groups.stream()
                .flatMap(group -> Arrays.stream(group.get(2).sharedItems(group.get(0).sharedItems(group.get(1)))))
                .map(Rucksack::charToPrio)
                .reduce(0, Integer::sum);;

        System.out.println(sum);

    }
}
