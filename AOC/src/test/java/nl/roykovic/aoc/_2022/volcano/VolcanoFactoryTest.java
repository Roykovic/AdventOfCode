package nl.roykovic.aoc._2022.volcano;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class VolcanoFactoryTest {
    @Test
    void testExamplePressureReleased() throws IOException {
        File input = new File("src/test/resources/2022/VolcanoTestInput.txt");
        Volcano volcano = new VolcanoFactory().generateFromFile(input);

        System.out.println(volcano.getValves().get(0).getMostOptimalValveFromHere(30, 0).getValue());
    }
}
