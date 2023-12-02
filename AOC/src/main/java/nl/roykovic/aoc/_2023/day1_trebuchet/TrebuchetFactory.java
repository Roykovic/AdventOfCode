package nl.roykovic.aoc._2023.day1_trebuchet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TrebuchetFactory {

    private static Pattern firstPartPattern = Pattern.compile("[0-9]");
    private static Pattern secondPartPattern = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");

    public IntStream generateFromFile(File file, boolean writtenWords) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines()
                .mapToInt(line -> getIndex(writtenWords? secondPartPattern: firstPartPattern, line));
    }

    public static int getIndex(Pattern pattern, String s) {
        Matcher matcher = pattern.matcher(s);

        matcher.find();

        String firstResult = matcher.group();

        int firstDigit = firstPartPattern.matcher(firstResult).matches()? Integer.parseInt(matcher.group()) : writtenDigits.valueOf(firstResult.toUpperCase()).value;
        int lastDigit = firstDigit;

        while (matcher.find()){
            String lastResult = matcher.group();
            lastDigit = firstPartPattern.matcher(lastResult).matches()? Integer.parseInt(matcher.group()) : writtenDigits.valueOf(lastResult.toUpperCase()).value;
        }

        return Integer.parseInt(firstDigit + String.valueOf(lastDigit));
    }

    private enum writtenDigits{

        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9);
        private final int value;

        writtenDigits(int value) {
            this.value = value;
        }


        public int getValue() {
            return value;
        }
    }

}
