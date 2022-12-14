package nl.roykovic.aoc._2022.monkey;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyFactoryTest {
    @Test
    void testExampleMonkeyBusiness() throws IOException {
        File input = new File("src/test/resources/2022/MonkeyTestInput.txt");
        List<Monkey> list = new MonkeyFactory().generateFromFile(input);

        for(int round = 0; round < 20; round++) {
            for (Monkey monkey : list) {
                monkey.act(3);
            }
        }

        assertEquals(list.get(0).getItems(), List.of(10L, 12L,  14L, 26L,34L));
        assertEquals(list.get(1).getItems(), List.of(245L, 93L, 53L, 199L, 115L));
        assertEquals(list.get(2).getItems(), new ArrayList<>());
        assertEquals(list.get(3).getItems(), new ArrayList<>());

        Long monkeyBusiness = list.stream().map(Monkey::getActivity).sorted(Comparator.reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b); //sort items high to low, get first two, multiply them

        assertEquals(10605, monkeyBusiness);
    }

    @Test
    void testActualMonkeyBusiness() throws IOException {
        File input = new ClassPathResource("2022/MonkeyInput.txt").getFile();
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
        File input = new File("src/test/resources/2022/MonkeyTestInput.txt");
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
        File input = new ClassPathResource("2022/MonkeyInput.txt").getFile();
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
