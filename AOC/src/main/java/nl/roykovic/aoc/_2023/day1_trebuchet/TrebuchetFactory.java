package nl.roykovic.aoc._2023.day1_trebuchet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TrebuchetFactory {

    public IntStream generateFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines()
                .map(StringUtils::getDigits)
                .map(string -> string.charAt(0) + "" + string.charAt(string.length() -1))
                .mapToInt(digit -> NumberUtils.parseNumber(digit, Integer.class));
    }
}
