package nl.roykovic.aoc._2022.day5_supplystacks;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CraneFactory {

    public Crane generateFromFile(Stream<String> input) {
        return input
                .filter(line -> line.contains("[") || line.contains("move")) //filtering out irrelevant lines
                .collect(Collectors.partitioningBy(line -> line.contains("[")))//creating a map with key = true -> crate, key = false -> instruction
                .entrySet().stream()
                .map(it -> it.getKey() ? buildCrateStacks(it.getValue()) : buildInstructions(it.getValue()))
                .flatMap(List::stream)
                .collect(
                        Collectors.teeing(
                                Collectors.filtering(entry -> entry instanceof CrateStack, Collectors.toList()), //Collecting Cratestacks
                                Collectors.filtering(entry -> entry instanceof CraneInstruction, Collectors.toList()),  //Collecting CraneInstructions
                                (crateStacks, craneInstructions) -> new Crane((List<CrateStack>) crateStacks, (List<CraneInstruction>) craneInstructions)));    //Feeding them into Crane constructor
    }

    private List<CrateStack> buildCrateStacks(List<String> crateStackLines) {
        List<CrateStack> crateStacks = Stream.generate(CrateStack::new)
                .limit((crateStackLines.stream()
                        .max(Comparator.comparingInt(String::length)).orElseThrow()
                        .length() +1) /4)
                .collect(Collectors.toList());

        Collections.reverse(crateStackLines);

        for (String line : crateStackLines) {
            int currentStack = 0;
            while (!StringUtils.isBlank(line)) {
                String crate = StringUtils.substring(line, 0, 4);
                if (crate.contains("[")) {
                    crateStacks.get(currentStack).push(crate.charAt(1));
                }
                line = StringUtils.substring(line, 4);
                currentStack++;
            }
        }
        return crateStacks;
    }

    private List<CraneInstruction> buildInstructions(List<String> instructionLines) {
        return instructionLines.stream().map(line ->
                new CraneInstruction( //a bit dirty, but the CraneInstruction can take a list of ints to fill its internal properties
                    Pattern.compile("\\d+")
                            .matcher(line)
                            .results()
                            .map(it -> Integer.parseInt(it.group())).toList()
                )
        ).toList();
    }
}