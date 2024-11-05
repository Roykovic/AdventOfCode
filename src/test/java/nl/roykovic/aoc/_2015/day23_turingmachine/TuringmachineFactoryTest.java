package nl.roykovic.aoc._2015.day23_turingmachine;

import nl.roykovic.aoc._2015.day23_turingmachine.operations.TuringOperation;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TuringmachineFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "TuringmachineTestInput.txt,true,2, 0, 0.0",
            "TuringmachineInput.txt,false,1, 184, 0.0",
            "TuringmachineInput.txt,false,1, 231, 1.0",
    })
    public void test(String filename, boolean test, int expectedA, int expectedB, double aStart) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);
        var output = new TuringmachineFactory().generate(input);

        int nextOpIndex = 0;

        var registers = new java.util.HashMap<>(Map.of(
                'a', aStart,
                'b', 0.0));

        while(nextOpIndex < output.size()){
            TuringOperation nextOp = output.get(nextOpIndex);

            Double curVal = registers.get(nextOp.register);
            registers.put(nextOp.register, nextOp.AddMultiplier(curVal));

            nextOpIndex += nextOp.jump(curVal);
        }

        assertEquals(expectedA, registers.get('a'));
        assertEquals(expectedB, registers.get('b'));
    }}
