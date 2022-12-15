package nl.roykovic.aoc._2015.present;

import nl.roykovic.aoc._2015.apartment.ApartmentFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseFactoryTest {
    @ParameterizedTest
    @CsvSource({
            ">,2",
            "^>v<,4",
            "^v^v^v^v^v,2"
    })
    void testExampleHouses(String input, int expectedNumberOfHouses) {
        int floor = new ApartmentFactory().generateFromString(input).sum();
        assertEquals(expectedNumberOfHouses, floor);
    }
}
