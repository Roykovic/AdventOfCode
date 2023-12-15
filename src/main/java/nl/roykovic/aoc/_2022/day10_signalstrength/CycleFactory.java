package nl.roykovic.aoc._2022.day10_signalstrength;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CycleFactory {
    public Program generateFromFile(List<String> lines){

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
