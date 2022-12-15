package nl.roykovic.aoc._2015.present;

import nl.roykovic.aoc._2015.apartment.ApartmentFactory;
import nl.roykovic.aoc.utils.Coord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseFactoryTest {
    @ParameterizedTest
    @CsvSource({
            ">,2",
            "^>v<,4",
            "^v^v^v^v^v,2"
    })
    void testExampleHouses(String input, int expectedNumberOfHouses) {
        int houses = new HouseFactory().generateFromString(input).size();
        assertEquals(expectedNumberOfHouses, houses);
    }
    @Test
    void testActualHouses() throws IOException {
        File input = new ClassPathResource("2015/HouseInput.txt").getFile();
        int houses = new HouseFactory().generateFromFile(input).size();
        assertEquals(2565, houses);
    }
}
