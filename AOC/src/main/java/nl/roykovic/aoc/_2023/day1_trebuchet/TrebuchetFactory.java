package nl.roykovic.aoc._2023.day1_trebuchet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TrebuchetFactory {

    private static final Pattern firstPartPattern = Pattern.compile("[0-9]");
    private static final Pattern secondPartPattern = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");

    public IntStream generateFromFile(Stream<String> input, boolean writtenWords) throws FileNotFoundException {
        return input
                .mapToInt(line -> getIndex(writtenWords? secondPartPattern: firstPartPattern, line));
    }

    public static int getIndex(Pattern p, String s) {

        Matcher m = p.matcher(s);

        List<String> matches = new ArrayList<>();

        int i = 0;
        while(m.find(i)) { // set start index for "find"
            matches.add(m.group());
            i = m.start() + 1; // update start index to start from beginning of last match + 1
        }

        String number =
                Stream.of(matches.get(0), matches.get(matches.size()-1))
                .map(it -> Optional.of(it)
                        .filter(NumberUtils::isCreatable)
                        .map(Integer::parseInt)
                        .orElseGet(() ->
                                writtenDigits.valueOf(it.toUpperCase()).getValue()))
                .map(Object::toString)
                .collect(Collectors.joining(""));

        return Integer.parseInt(number);
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
