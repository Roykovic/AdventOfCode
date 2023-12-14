package nl.roykovic.aoc._2023.day14_rocks;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class RocksFactoryTest {
@ParameterizedTest
    @CsvSource({
            "RocksTestInput.txt,true,136",
            "RocksInput.txt,false,109385",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.getFileAsString(2023, filename, test);
        var output = new RocksFactory().generate(input);

        assertEquals(expected, output);
    }}
