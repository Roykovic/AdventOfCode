package nl.roykovic.aoc._2022.day21_monkeymath;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyMathFactoryTest {

    @BeforeAll
    static void setup(){
        License.iConfirmNonCommercialUse("roykovic");
    }
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
        monkeyMap.put("humn", "z");

        while(equation.matches(".*[a-y].*")) {
            for (Map.Entry<String, String> entry : monkeyMap.entrySet()) {
                equation = StringUtils.replace(equation, entry.getKey(), " (" + entry.getValue() + ") ");
            }
        }

        //the one NOT containing our variable 'z'
        Expression rightSide = new Expression(StringUtils.substringAfter(equation, "="));

        //Substracting the right side from the leftside to get ..z = 0
        String leftSide = StringUtils.substringBefore(equation, "=") + " - " + rightSide.calculate();

        //Solve for Z
        Expression e = new Expression("solve( " + leftSide + ", z, 0, " + Integer.MAX_VALUE + ")");
        assertEquals(301, e.calculate());
    }

    @Test
    void testActualEquationSecondWay() throws IOException {
        File input = new ClassPathResource("2022/MonkeyMathInput.txt").getFile();
        Map<String, String> monkeyMap = new MonkeyMathFactory().generateFromFile(input);

        String equation = monkeyMap.get("root").replace('+', '=');
        monkeyMap.put("humn", "z");

        while(equation.matches(".*[a-y].*")) {
            for (Map.Entry<String, String> entry : monkeyMap.entrySet()) {
                equation = StringUtils.replace(equation, entry.getKey(), " (" + entry.getValue() + ") ");
            }
        }

        //the one NOT containing our variable 'z'
        Expression rightSide = new Expression(StringUtils.substringAfter(equation, "="));

        //Substracting the right side from the leftside to get ..z = 0
        String leftSide = StringUtils.substringBefore(equation, "=") + " - " + BigDecimal.valueOf(rightSide.calculate()).toPlainString();

        //Solve for Z
        Expression e = new Expression("solve( " + leftSide + ", z, 0, " + Long.MAX_VALUE + ")");

        assertEquals(3327575724809L,Math.round(e.calculate()));
    }
}
