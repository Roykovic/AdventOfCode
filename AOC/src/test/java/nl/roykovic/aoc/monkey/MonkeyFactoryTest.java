package nl.roykovic.aoc.monkey;

import nl.roykovic.aoc.rucksack.Rucksack;
import nl.roykovic.aoc.rucksack.RucksackFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyFactoryTest {
    @Test
    void testExampleMonkeyBusiness() throws IOException, ScriptException {
        File input = new File("src/test/resources/MonkeyTestInput.txt");
        List<Monkey> list = new MonkeyFactory().generateFromFile(input);

        for(int round = 0; round < 20; round++) {
            for (Monkey monkey : list) {
                monkey.act();
            }
        }

        for(Monkey monkey: list){
            System.out.println("Monkey " + list.indexOf(monkey) + " " + monkey.getItems());
        }

    }

}
