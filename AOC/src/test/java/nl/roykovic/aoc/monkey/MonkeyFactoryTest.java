package nl.roykovic.aoc.monkey;

import nl.roykovic.aoc.rucksack.Rucksack;
import nl.roykovic.aoc.rucksack.RucksackFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyFactoryTest {
    @Test
    void testExampleMonkeyBusiness() throws IOException {
        File input = new File("src/test/resources/MonkeyTestInput.txt");
        List<Monkey> list = new MonkeyFactory().generateFromFile(input);

        for(int round = 0; round < 20; round++) {
            for (Monkey monkey : list) {
                monkey.act(3);
            }
        }

        assertEquals(list.get(0).getItems(), List.of(new BigInteger("10"), new BigInteger("12"), new BigInteger("14"), new BigInteger("26"), new BigInteger("34")));
        assertEquals(list.get(1).getItems(), List.of(new BigInteger("245"), new BigInteger("93"), new BigInteger("53"), new BigInteger("199"), new BigInteger("115")));
        assertEquals(list.get(2).getItems(), new ArrayList<>());
        assertEquals(list.get(3).getItems(), new ArrayList<>());

        Long monkeyBusiness = list.stream().map(Monkey::getActivity).sorted(Comparator.reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b); //sort items high to low, get first two, multiply them

        assertEquals(10605, monkeyBusiness);
    }

    @Test
    void testActualMonkeyBusiness() throws IOException {
        File input = new ClassPathResource("MonkeyInput.txt").getFile();
        List<Monkey> list = new MonkeyFactory().generateFromFile(input);

        for(int round = 0; round < 20; round++) {
            for (Monkey monkey : list) {
                monkey.act(3);
            }
        }

        Long monkeyBusiness = list.stream().map(Monkey::getActivity).sorted(Comparator.reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b); //sort items high to low, get first two, multiply them

        assertEquals(58794, monkeyBusiness);
    }

    @Test
    void testExampleMonkeyBusinessWithoutWorryDecrease() throws IOException {
        File input = new File("src/test/resources/MonkeyTestInput.txt");
        List<Monkey> list = new MonkeyFactory().generateFromFile(input);

        for(int round = 0; round < 10000; round++) {
            for (Monkey monkey : list) {
                monkey.act(1);
            }
        }

        Long monkeyBusiness = list.stream().map(Monkey::getActivity).sorted(Comparator.reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b); //sort items high to low, get first two, multiply them

        assertEquals(2713310158L, monkeyBusiness);
    }

    @Test
    void testActualMonkeyBusinessWithoutWorryDecrease() throws IOException {
        File input = new ClassPathResource("MonkeyInput.txt").getFile();
        List<Monkey> list = new MonkeyFactory().generateFromFile(input);

        for(int round = 0; round < 10000; round++) {
            for (Monkey monkey : list) {
                monkey.act(1);
            }
        }

        Long monkeyBusiness = list.stream().map(Monkey::getActivity).sorted(Comparator.reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b); //sort items high to low, get first two, multiply them

        assertEquals(20151213744L, monkeyBusiness);
    }
}
