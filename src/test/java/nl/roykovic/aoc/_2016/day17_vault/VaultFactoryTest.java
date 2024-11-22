package nl.roykovic.aoc._2016.day17_vault;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class VaultFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "ihgpwlah,DDRRRD",
            "kglvqrro,DDUDRLRRUDRD",
            "ulqzkmiv,DRURDRUDDLLDLUURRDULRLDUUDDDRR",
            "dmypynyp,-1",
    })
    public void test(String input, int expected) {
        var output = new VaultFactory().generate(input);

        assertEquals(expected, output);
    }
}
