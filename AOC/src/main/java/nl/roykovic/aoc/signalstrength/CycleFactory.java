package nl.roykovic.aoc.signalstrength;

import nl.roykovic.aoc.rucksack.Rucksack;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CycleFactory {
    public Program generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        List<CPUInstruction> instructions = new ArrayList<>();

        for(String line: lines){
            String[] instruction = line.split(" ");
            String instructionTypeString = instruction[0];
            int registerIncrease = NumberUtils.toInt(instruction.length == 2?instruction[1]:null);

            InstructionType instructionType = InstructionType.valueOf(instructionTypeString);

            instructions.add(new CPUInstruction(instructionType, registerIncrease));
        }
        return new Program(instructions);
    }
}
