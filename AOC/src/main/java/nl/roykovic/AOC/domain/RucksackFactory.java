package nl.roykovic.AOC.domain;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RucksackFactory {

    public List<Rucksack> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        List<Rucksack> rucksackList = new ArrayList<>();

        for(String line: lines){
            Character[] firstCompartment = ArrayUtils.toObject(line.substring(0, line.length()/2).toCharArray());
            Character[] secondCompartment = ArrayUtils.toObject(line.substring(line.length()/2).toCharArray());

            Rucksack rucksack = new Rucksack(firstCompartment, secondCompartment);
            rucksackList.add(rucksack);
//            System.out.println(rucksack);
        }
        return rucksackList;
    }
}
