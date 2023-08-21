package nl.roykovic.aoc._2015.day6_lights;

import nl.roykovic.aoc._2015.day5_naughtystrings.NaughtyOrNiceFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightFactoryTest {
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "turn on 0,0 through 999,999; 1000000",
            "toggle 0,0 through 999,0; 1000",
            "turn off 499,499 through 500,500; 0",
            "turn on 499,499 through 500,500; 4",
    })
    void testExampleLightsOn(String input, int expectedLightsOn) {
        assertEquals(expectedLightsOn,
                Arrays.stream(new LightFactory().generateFromStream(Stream.of(input), false)).flatMapToInt(Arrays::stream).filter(it -> it ==1).count());
    }
    @Test
    void testActualLightsOn() throws IOException {
        File input = new ClassPathResource("2015/LightInput.txt").getFile();
        int[][] lights = new LightFactory().generateFromFile(input, false);

        assertEquals(400410,Arrays.stream(lights).flatMapToInt(Arrays::stream).filter(it -> it ==1).count());
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "turn on 0,0 through 0,0; 1",
            "toggle 0,0 through 999,999; 2000000",
    })
    void testExampleLightsBrightness(String input, int expectedLightsOn) {
        assertEquals(expectedLightsOn,
                Arrays.stream(new LightFactory().generateFromStream(Stream.of(input), true)).flatMapToInt(Arrays::stream).sum());
    }

    @Test
    void testActualLightsBrightness() throws IOException {
        File input = new ClassPathResource("2015/LightInput.txt").getFile();
        int[][] lights = new LightFactory().generateFromFile(input, true);

        assertEquals(15343601,
                Arrays.stream(lights).flatMapToInt(Arrays::stream).sum());
    }
}
