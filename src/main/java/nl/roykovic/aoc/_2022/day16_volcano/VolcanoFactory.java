package nl.roykovic.aoc._2022.day16_volcano;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VolcanoFactory {
    public Volcano generateFromFile(List<String> lines){
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
