package nl.roykovic.aoc._2022.day22_monkeymap;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyMapFactoryTest {
    @Test
    void testExampleRoute() throws IOException{
        File input = new File("src/test/resources/2022/MonkeyMapTestInput.txt");
        MonkeyMap monkeyMap = new MonkeyMapFactory().generateFromFile(input);

        monkeyMap.move();

        assertEquals(6032, 1000*(monkeyMap.getCurrentY()+1) + 4* (monkeyMap.getCurrentX()+1) + monkeyMap.getCurrentDirection());
    }

    @Test
    void testActualRoute() throws IOException{
        File input = new ClassPathResource("2022/MonkeyMapInput.txt").getFile();
        MonkeyMap monkeyMap = new MonkeyMapFactory().generateFromFile(input);

        monkeyMap.move();

        assertEquals(58, monkeyMap.getCurrentY()+1);
        assertEquals(62, monkeyMap.getCurrentX()+1);
        assertEquals(0, monkeyMap.getCurrentDirection());

        assertEquals(58248, 1000*(monkeyMap.getCurrentY()+1) + 4* (monkeyMap.getCurrentX()+1) + monkeyMap.getCurrentDirection());
    }
}
