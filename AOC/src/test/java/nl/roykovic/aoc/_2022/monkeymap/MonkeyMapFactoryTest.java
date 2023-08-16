package nl.roykovic.aoc._2022.monkeymap;

import nl.roykovic.aoc._2022.monkeymath.MonkeyMathFactory;
import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyMapFactoryTest {
    @Test
    void testExampleRoute() throws IOException{
        File input = new File("src/test/resources/2022/MonkeyMapTestInput.txt");
        MonkeyMap monkeyMap = new MonkeyMapFactory().generateFromFile(input);

        monkeyMap.move();

        System.out.println(monkeyMap.getCurrentX());
        System.out.println(monkeyMap.getCurrentY());
        System.out.println(monkeyMap.getCurrentDirection());

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
