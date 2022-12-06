package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.Elf;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class ElfFactory {
    public List<Elf> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        List<Elf> elfList = new ArrayList<>() {
        };

        for(String line: lines){
            Elf elf;
            if(elfList.isEmpty() || StringUtils.isBlank(line)){
                elf = new Elf();
                elfList.add(elf);
            }
            else{
                elf = elfList.get(elfList.size() -1);
                elf.addCalories(NumberUtils.createLong(line));
            }

        }
        return elfList;
    }
}
