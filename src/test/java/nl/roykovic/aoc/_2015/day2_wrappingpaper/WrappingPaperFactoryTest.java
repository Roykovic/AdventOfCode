package nl.roykovic.aoc._2015.day2_wrappingpaper;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrappingPaperFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "2x3x4,58",
            "1x1x10,43"
    })
    void testExampleSurface(String input, int expectedSurface) {

        int surface = WrappingPaperFactory.calculateSurfaceBySides(Arrays.stream(input.split("x")).mapToInt(NumberUtils::toInt));

        assertEquals(expectedSurface, surface);
    }

    @Test
    void testActualSurface() throws IOException {
        File input = new ClassPathResource("2015/WrappingPaperInput.txt").getFile();
        int surface = new WrappingPaperFactory().generateFromFile(input).mapToInt(WrappingPaperFactory::calculateSurfaceBySides).sum();
        assertEquals(1588178, surface);
    }

    @ParameterizedTest
    @CsvSource({
            "2x3x4,34",
            "1x1x10,14"
    })
    void testExampleRibbonLength(String input, int expectedSurface) {

        int surface = WrappingPaperFactory.calculateRibbonLengthBySides(Arrays.stream(input.split("x")).mapToInt(NumberUtils::toInt));

        assertEquals(expectedSurface, surface);
    }

    @Test
    void testActualRibbonLength() throws IOException {
        File input = new ClassPathResource("2015/WrappingPaperInput.txt").getFile();
        int surface = new WrappingPaperFactory().generateFromFile(input).mapToInt(WrappingPaperFactory::calculateRibbonLengthBySides).sum();
        assertEquals(3783758, surface);
    }
}
