package nl.roykovic.aoc._2022.day1_elf;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElfFactory {
    public List<Elf> generateFromFile(List<String> lines) throws FileNotFoundException {

        List<Elf> elfList = new ArrayList<>();

        lines.forEach(it -> {
            Elf elf;
            if(elfList.isEmpty() || StringUtils.isBlank(it)){
                elf = new Elf();
                elfList.add(elf);
            }
            else{
                elf = elfList.get(elfList.size() -1);
                elf.addCalories(NumberUtils.createLong(it));
            }
        });


        Collections.sort(elfList);
        Collections.reverse(elfList);

        return elfList;
    }
}
