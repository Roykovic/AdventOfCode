package nl.roykovic.aoc._2015.day18_lightsparttwo;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightFactoryTest {
    @Test
    void testExampleLightsOn() throws IOException {
        LightFactory lightFactory = new LightFactory();

        File input = new File("src/test/resources/2015/LightsPartTwoExampleInput.txt");
        boolean[][] combinations = lightFactory.generateFromFile(input, false);

        for (int i = 0; i < 4; i++) {
            combinations = lightFactory.animate(combinations, false);
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
        boolean[][] combinations = lightFactory.generateFromFile(input, false);

        for (int i = 0; i < 100; i++) {
            combinations = lightFactory.animate(combinations, false);
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

    @Test
    void testExampleLightsOnWithCorners() throws IOException {
        LightFactory lightFactory = new LightFactory();

        File input = new File("src/test/resources/2015/LightsPartTwoExampleInput.txt");
        boolean[][] combinations = lightFactory.generateFromFile(input, true);

        for (int i = 0; i < 5; i++) {
            combinations = lightFactory.animate(combinations, true);
        }
        int counter = 0;

        for (boolean[] bArr : combinations) {
            for (boolean b : bArr) {
                if (b) {
                    counter++;
                }
            }
        }

        assertEquals(17, counter);
    }

    @Test
    void testActualLightsOnWithCorners() throws IOException {
        LightFactory lightFactory = new LightFactory();

        File input = new ClassPathResource("2015/LightsPartTwoInput.txt").getFile();
        boolean[][] combinations = lightFactory.generateFromFile(input, true);

        for (int i = 0; i < 100; i++) {
            combinations = lightFactory.animate(combinations, true);
        }
        int counter = 0;

        for (boolean[] bArr : combinations) {
            for (boolean b : bArr) {
                if (b) {
                    counter++;
                }
            }
        }

        assertEquals(886, counter);
    }
}
