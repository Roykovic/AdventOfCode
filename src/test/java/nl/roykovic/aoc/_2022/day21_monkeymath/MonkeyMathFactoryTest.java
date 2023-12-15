package nl.roykovic.aoc._2022.day21_monkeymath;

import nl.roykovic.aoc.utils.FileReaderService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    @ParameterizedTest
    @CsvSource({
            "MonkeyMathTestInput.txt,true,152",
            "MonkeyMathInput.txt,false,66174565793494"
    })
    void testEquation(String filename, boolean test, long expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        Map<String, String> monkeyMap = new MonkeyMathFactory().generateFromFile(input);

        String equation = monkeyMap.get("root");

        while(equation.matches(".*[a-z].*")) {
            for (Map.Entry<String, String> entry : monkeyMap.entrySet()) {
                equation = StringUtils.replace(equation, entry.getKey(), " (" + entry.getValue() + ") ");
            }
        }

        Expression e = new Expression(equation);
        assertEquals(expected, e.calculate());

    }
    @ParameterizedTest
    @CsvSource({
            "MonkeyMathTestInput.txt,true,301",
            "MonkeyMathInput.txt,false,3327575724809"
    })
    void testEquationSecondWay(String filename, boolean test, long expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
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

        assertEquals(expected,Math.round(e.calculate()));
    }
}
