package nl.roykovic.aoc._2023.day18_lagoon;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;
import nl.roykovic.aoc.utils.Utils;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LagoonFactory {
    public BigInteger generate(Stream<String> input) {

        Coord start = new Coord(0, 0);
        List<CoordRange> trenches = new ArrayList<>();

        for (String[] arr : input.map(it -> it.split(" ")).toList()) {

            Direction direction = Direction.valueOf(arr[0]);
            int amount = Integer.parseInt(arr[1]);
            long additionalX = (long) direction.getAdditionalX() * (amount-1);
            long additionalY = (long) direction.getAdditionalY() * (amount-1);

            start.move(direction);
            Coord newStart =  new Coord(start.getX() + additionalX, start.getY() + additionalY);

            trenches.add(new CoordRange(new Coord(start),newStart, direction));

            start = new Coord(newStart);
        }

        //Picks theorem again, we can calculate area, we have boundary points. We want to know Interior points + boundary points:
        var area = Utils.shoelaceAreaFromRange(trenches);
        var boundaryPoints = BigInteger.valueOf(trenches.stream().mapToLong(CoordRange::getAmountOfCoords).sum() + trenches.size());


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
