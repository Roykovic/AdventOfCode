package nl.roykovic.aoc._2016.day12_assembunny;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
public class AssembunnyFactory {
    public int generate(List<String> input, int cValue) {

        Map<String, Integer> registers = new HashMap<>();

        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", cValue);
        registers.put("d", 0);

        for(int i = 0; i < input.size();){
            String curInstruction = input.get(i);

            if(curInstruction.contains("cpy")){
                String val = StringUtils.substringBetween(curInstruction,"cpy ", " ").trim();
                String register = StringUtils.substringAfterLast(curInstruction, " ").trim();

                int value;
                if(StringUtils.equalsAny(val, "a", "b", "c")){
                    value = registers.get(val);
                }
                else{
                    value = Integer.parseInt(val);
                }

                registers.put(register, value);
                i++;
            }
            else if(curInstruction.contains("inc")){
                String register = StringUtils.substringAfter(curInstruction, "inc ").trim();
                registers.put(register, registers.get(register) +1);
                i++;
            }
            else if(curInstruction.contains("dec")){
                String register = StringUtils.substringAfter(curInstruction, "dec ").trim();
                registers.put(register, registers.get(register) -1);
                i++;
            }
            else if(curInstruction.contains("jnz")){
                String val = StringUtils.substringBetween(curInstruction,"jnz ", " ").trim();
                int jump = Integer.parseInt(StringUtils.substringAfter(curInstruction, val).trim());

                int value;
                if(StringUtils.equalsAny(val, "a", "b", "c", "d")){
                    value = registers.get(val);
                }
                else{
                    value = Integer.parseInt(val);
                }

                if(value != 0){
                    i+=jump;
                }
                else{
                    i++;
                }
            }
            else{
                System.out.println(curInstruction);
            }
        }

        return registers.get("a");
    }
}
