package nl.roykovic.aoc._2022.day11_monkey;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "MonkeyTestInput.txt,true,10605",
            "MonkeyInput.txt,false,58794"
    })
    void testMonkeyBusiness(String filename, boolean test, long expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        List<Monkey> list = new MonkeyFactory().generateFromFile(input);

        for(int round = 0; round < 20; round++) {
            for (Monkey monkey : list) {
                monkey.act(3);
            }
        }

        Long monkeyBusiness = list.stream()
                .map(Monkey::getActivity)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce(1L, (a, b) -> a * b); //sort items high to low, get first two, multiply them

        assertEquals(expected, monkeyBusiness);
    }

    @ParameterizedTest
    @CsvSource({
            "MonkeyTestInput.txt,true,2713310158",
            "MonkeyInput.txt,false,20151213744"
    })
    void testMonkeyBusinessWithoutWorryDecrease(String filename, boolean test, long expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        List<Monkey> list = new MonkeyFactory().generateFromFile(input);

        for(int round = 0; round < 10000; round++) {
            for (Monkey monkey : list) {
                monkey.act(1);
            }
        }

        Long monkeyBusiness = list.stream()
                .map(Monkey::getActivity)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce(1L, (a, b) -> a * b); //sort items high to low, get first two, multiply them

        assertEquals(expected, monkeyBusiness);
    }
}
