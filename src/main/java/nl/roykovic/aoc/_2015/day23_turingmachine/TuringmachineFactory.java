package nl.roykovic.aoc._2015.day23_turingmachine;

import nl.roykovic.aoc._2015.day23_turingmachine.operations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
public class TuringmachineFactory {
    public List<TuringOperation> generate(Stream<String> input) {

        return input.map(it -> it.split(" ")).map(it -> {
            String operation = it[0];
            Character register = Objects.equals(it[1].charAt(0), 'a') || Objects.equals(it[1].charAt(0), 'b') ? it[1].charAt(0):null;
            int jump = it.length ==3? new BigDecimal(it[2]).intValue() : it[1].contains("+") || it[1].contains("-")? new BigDecimal(it[1]).intValue(): 1;

            TuringOperation op = switch (operation) {
                case "hlf" -> new HalfOperation();
                case "tpl" -> new TripleOperation();
                case "inc" -> new IncrementOperation();
                case "jmp" -> new JumpOperation();
                case "jie" -> new JumpEvenOperation();
                case "jio" -> new JumpOneOperation();
                default -> throw new RuntimeException();
            };
            op.register = register;
            op.jump = jump;

            return op;
        }).toList();
    }
}
