package nl.roykovic.aoc._2016.day15_bouncyballs;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class BouncyBallsFactory {
    public List<Disc> generate(Stream<String> input) {
        return input.map(it -> {
            int positions = Integer.parseInt(StringUtils.substringBetween(it, "has ", " positions"));
            int startPos = Integer.parseInt(StringUtils.substringBetween(it, " it is at position ", "."));

            return new Disc(positions, startPos);
        }).toList();
    }
}
