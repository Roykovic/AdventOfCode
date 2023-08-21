package nl.roykovic.aoc._2022.day15_beacon;

import nl.roykovic.aoc.utils.Coord;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensorFactory {
    public List<Sensor> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        List<Sensor> sensors = new ArrayList<>();

        for(String line: lines){
            Pattern p = Pattern.compile("-?\\d+");
            Matcher m = p.matcher(line);
            List<Long> list = new ArrayList<>();
            while (m.find()) {
                list.add(NumberUtils.toLong(m.group()));
            }

            sensors.add(new Sensor(new Coord(list.get(0), list.get(1)), new Beacon(new Coord(list.get(2), list.get(3)))));
        }
        return sensors;
    }
}
