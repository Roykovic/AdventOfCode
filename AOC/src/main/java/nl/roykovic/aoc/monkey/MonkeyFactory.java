package nl.roykovic.aoc.monkey;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyFactory {
    List<Monkey> monkeyList = new ArrayList<>();

    public List<Monkey> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        Monkey curMonkey = null;
        for(String line: lines){
            if(line.trim().startsWith("Monkey")){
                curMonkey = new Monkey();
                monkeyList.add(curMonkey);
            }
            if(line.trim().startsWith("Starting items")){
                List<Long> startingItems = new ArrayList<>();

                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(line);

                while(m.find()){
                    startingItems.add(NumberUtils.toLong(m.group()));
                }

                curMonkey.setItems(startingItems);
            }
            if(line.trim().startsWith("Operation")){
                curMonkey.setOperation(line.trim().split(" ", 2)[1]);
            }
            if(line.trim().startsWith("Test")){
                curMonkey.setTest(line.trim().split(" ", 2)[1]);
            }
            if(line.trim().startsWith("If true")){
                curMonkey.setTestTrueNumber(line.charAt(line.length() - 1) - '0');
            }
            if(line.trim().startsWith("If false")){
                curMonkey.setTestFalseNumber(line.charAt(line.length() - 1) - '0');
            }
        }

        for(Monkey monkey: monkeyList){
            setTestTrueAndFalseMonkeys(monkey);
        }
        return monkeyList;
    }

    private void setTestTrueAndFalseMonkeys(Monkey monkey){
        monkey.setTestTrue(monkeyList.get(monkey.getTestTrueNumber()));
        monkey.setTestFalse(monkeyList.get(monkey.getTestFalseNumber()));
    }
}