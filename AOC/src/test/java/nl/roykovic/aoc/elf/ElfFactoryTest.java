package nl.roykovic.aoc.elf;

import nl.roykovic.aoc.elf.Elf;
import nl.roykovic.aoc.elf.ElfFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElfFactoryTest {

    @Test
    void testExampleCalories() throws IOException {
        File input = new File("src/test/resources/elf/ElfTestInput.txt");
        List<Elf> list = new ElfFactory().generateFromFile(input);

        Collections.sort(list);
        Collections.reverse(list);

        assertEquals(24000, list.get(0).getCalories());
    }

    @Test
    void testActualCalories() throws IOException {
        File input = new ClassPathResource("ElfCalorieInput.txt").getFile();
        List<Elf> list = new ElfFactory().generateFromFile(input);

        Collections.sort(list);
        Collections.reverse(list);

        assertEquals(70116, list.get(0).getCalories());

    }

    @Test
    void testExampleTopThreeCalories() throws IOException {
        File input = new File("src/test/resources/elf/ElfTestInput.txt");
        List<Elf> list = new ElfFactory().generateFromFile(input);

        Collections.sort(list);
        Collections.reverse(list);

        long topThree = list.subList(0, 3).stream().mapToLong(Elf::getCalories).reduce(0, Long::sum);

        assertEquals(45000, topThree);
    }

    @Test
    void testActualTopThreeCalories() throws IOException {
        File input = new ClassPathResource("ElfCalorieInput.txt").getFile();
        List<Elf> list = new ElfFactory().generateFromFile(input);

        Collections.sort(list);
        Collections.reverse(list);

        long topThree = list.subList(0, 3).stream().mapToLong(Elf::getCalories).reduce(0, Long::sum);

        assertEquals(206582, topThree);

    }
}
