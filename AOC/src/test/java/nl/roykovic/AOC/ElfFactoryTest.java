package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.Elf;
import nl.roykovic.AOC.domain.ElfFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ElfFactoryTest {

    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("ElfCalorieInput.txt").getFile();
        List<Elf> list = new ElfFactory().generateFromFile(input);

        System.out.println(list.size());
        System.out.println(list);
        Collections.sort(list);
        Collections.reverse(list);
        System.out.println(list);

    }
}
