package nl.roykovic.aoc._2022.volcano;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VolcanoFactory {
    public Volcano generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        Volcano volcano = new Volcano();

        List<Valve> valves = new ArrayList<>();
        for(String line: lines){
            valves.add(new Valve(NumberUtils.toInt(line.substring(23, line.indexOf(';')))));
        }
        for(int i = 0; i < lines.size(); i++){
            List<Valve> neighbours = Arrays.stream(StringUtils.reverse(lines.get(i)).split(" ,")).map(String::trim).mapToInt(it -> it.charAt(0) - 'A').mapToObj(valves::get).toList();
            valves.get(i).setNeighbours(neighbours);
        }

        volcano.setValves(valves);
        return volcano;
    }
}
