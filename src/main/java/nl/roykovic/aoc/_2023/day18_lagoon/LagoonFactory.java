package nl.roykovic.aoc._2023.day18_lagoon;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import nl.roykovic.aoc.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class LagoonFactory {
    public BigInteger generate(Stream<String> input) {

        Coord start = new Coord(0, 0);
        List<Coord> trenches = new ArrayList<>();

        for (String[] arr : input.map(it -> it.split(" ")).toList()) {
            List<Coord> coords = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
                start.move(Direction.valueOf(arr[0]));
                coords.add(new Coord(start));
            }
            trenches.addAll(coords);
        }

        //Picks theorem again, we can calculate area, we have boundary points. We want to know Interior points + boundary points:
//        drawGrid(trenches);
        var area = BigDecimal.valueOf(Utils.shoelaceArea(trenches)).toBigInteger(); ;
        var boundaryPoints = BigInteger.valueOf(trenches.size());


        var interiorPoints = boundaryPoints.divide(BigInteger.TWO).subtract(BigInteger.ONE).subtract(area).abs();

        return boundaryPoints.add(interiorPoints);

    }

    public static Stream<String> sanitizeInput(Stream<String> input) {
        return input
                .map(it -> it.split(" "))
                .map(it -> it[2])
                .map(it -> it.replace("(", "").replace(")", ""))
                .map(it ->
                        Direction.values()[Integer.parseInt(String.valueOf(it.charAt(it.length() - 1)))]
                        + " " +
                        Integer.decode(it.substring(0, it.length() - 1))
                );
    }
}
