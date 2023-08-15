package nl.roykovic.aoc._2022.monkeymath;

import nl.roykovic.aoc._2022.lavadroplets.LavaDropletsFactory;
import nl.roykovic.aoc.utils.Face;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.core.io.ClassPathResource;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyMathFactoryTest {
    @Test
    void testExampleEquation() throws IOException{
        File input = new File("src/test/resources/2022/MonkeyMathTestInput.txt");
        Map<String, String> monkeyMap = new MonkeyMathFactory().generateFromFile(input);

        String equation = monkeyMap.get("root");

        while(equation.matches(".*[a-z].*")) {
            for (Map.Entry<String, String> entry : monkeyMap.entrySet()) {
                equation = StringUtils.replace(equation, entry.getKey(), " (" + entry.getValue() + ") ");
            }
        }

        Expression e = new Expression(equation);
        assertEquals(152, e.calculate());

    }

    @Test
    void testActualEquation() throws IOException {
        File input = new ClassPathResource("2022/MonkeyMathInput.txt").getFile();
        Map<String, String> monkeyMap = new MonkeyMathFactory().generateFromFile(input);

        String equation = monkeyMap.get("root");

        while(equation.matches(".*[a-z].*")) {
            for (Map.Entry<String, String> entry : monkeyMap.entrySet()) {
                equation = StringUtils.replace(equation, entry.getKey(), " (" + entry.getValue() + ") ");
            }
        }

        Expression e = new Expression(equation);
        assertEquals(66174565793494L, e.calculate());
    }

    @Test
    void testExampleEquationSecondWay() throws IOException {
        File input = new File("src/test/resources/2022/MonkeyMathTestInput.txt");
        Map<String, String> monkeyMap = new MonkeyMathFactory().generateFromFile(input);

        String equation = monkeyMap.get("root").replace('+', '=');

        while(equation.matches(".*[a-z].*")) {
            for (Map.Entry<String, String> entry : monkeyMap.entrySet()) {
                equation = StringUtils.replace(equation, entry.getKey(), " (" + entry.getValue() + ") ");
            }
        }

        Expression e = new Expression(equation);
        assertEquals(301, e.calculate());
    }
}
