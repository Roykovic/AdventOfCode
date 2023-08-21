package nl.roykovic.aoc._2022.day5_supplystacks;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CraneFactory {

    public Crane generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = new ArrayList<>(reader.lines().toList());

        List<CrateStack> crateStacks = new ArrayList<>();
        List<CraneInstruction> instructions = new ArrayList<>();

        List<String> crateStackLines = new ArrayList<>(lines.stream().filter(line -> line.contains("[")).toList());
        Collections.reverse(crateStackLines);
        for(int i = 0; i < (crateStackLines.get(0).length()+1)/4; i++){
            crateStacks.add(new CrateStack());
        }

        for(String line : crateStackLines){
            int currentStack = 0;
            while(!StringUtils.isBlank(line)){
                String crate = StringUtils.substring(line,0,4);
                if(crate.contains("[")){
                    crateStacks.get(currentStack).push(crate.charAt(1));
                }
                line = StringUtils.substring(line,4);
                currentStack++;
            }
        }

        List<String> instructionLines = lines.stream().filter(line -> line.contains("move")).toList();
        for(String line : instructionLines){
            List<Integer> numbers = new ArrayList<>();

            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(line);

            while(m.find()){
                numbers.add(Integer.parseInt(m.group()));
            }
            instructions.add(new CraneInstruction(numbers.get(0), numbers.get(1), numbers.get(2)));
        }

        return new Crane(crateStacks, instructions);
    }
}
