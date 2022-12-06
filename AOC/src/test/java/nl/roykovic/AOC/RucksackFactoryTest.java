package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.RockPaperScissors.RPSGame;
import nl.roykovic.AOC.domain.RockPaperScissors.RPSGameFactory;
import nl.roykovic.AOC.domain.Rucksack;
import nl.roykovic.AOC.domain.RucksackFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RucksackFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("RucksackInput.txt").getFile();
        List<Rucksack> list = new RucksackFactory().generateFromFile(input);

        int sum = list.stream().flatMap(rucksack -> Arrays.stream(rucksack.sharedItems())).map(Rucksack::charToPrio).reduce(0, Integer::sum);

        System.out.println(sum);

    }
}
