package nl.roykovic.aoc._2015.day18_lightsparttwo;

import nl.roykovic.aoc._2015.day17_eggnog.EggnogContainerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightFactoryTest {
    @Test
    void testExampleLightsOn() throws IOException {
        LightFactory lightFactory = new LightFactory();

        File input = new File("src/test/resources/2015/LightsPartTwoExampleInput.txt");
        boolean[][] combinations = lightFactory.generateFromFile(input);

        for (int i = 0; i < 4; i++) {
            combinations = lightFactory.animate(combinations);
        }
        int counter = 0;

        for (boolean[] bArr : combinations) {
            for (boolean b : bArr) {
                if (b) {
                    counter++;
                }
            }
        }

        assertEquals(4, counter);
    }

    @Test
    void testActualLightsOn() throws IOException {
        LightFactory lightFactory = new LightFactory();

        File input = new ClassPathResource("2015/LightsPartTwoInput.txt").getFile();
        boolean[][] combinations = lightFactory.generateFromFile(input);

        for (int i = 0; i < 100; i++) {
            combinations = lightFactory.animate(combinations);
        }
        int counter = 0;

        for (boolean[] bArr : combinations) {
            for (boolean b : bArr) {
                if (b) {
                    counter++;
                }
            }
        }

        assertEquals(821, counter);
    }
}
