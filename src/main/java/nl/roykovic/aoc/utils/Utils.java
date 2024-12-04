package nl.roykovic.aoc.utils;

import nl.roykovic.aoc._2023.day18_lagoon.CoordRange;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Utils {


    public static char[][] rotateGridClockwise(char[][] input) {
        char[][] output = new char[input[0].length][input.length];

        for (int y = 0; y < output.length; y++) {
            for (int x = 0; x < output[y].length; x++) {
                output[y][x] = input[(output[y].length - 1) - x][y];
            }
        }

        return output;
    }

    public static char[][] rotateGridAntiClockwise(char[][] input) {
        char[][] output = new char[input[0].length][input.length];

        for (int y = 0; y < input[0].length; y++) {
            for (int x = input.length - 1; x >= 0; x--) {
                output[y][x] = input[x][(output[y].length - 1) - y];
            }
        }
        return output;
    }

    public static Stream<List<Object>> cartesianProduct(List<List<Object>> sets, int index) {
        if (index == sets.size()) {
            List<Object> emptyList = new ArrayList<>();
            return Stream.of(emptyList);
        }
        List<Object> currentSet = sets.get(index);
        return currentSet.stream().flatMap(element -> cartesianProduct(sets, index+1)
                .map(list -> {
                    List<Object> newList = new ArrayList<>(list);
                    newList.add(0, element);
                    return newList;
                }));
    }
    public static Long lcm(Long long1, Long long2) {
        BigInteger number1 = BigInteger.valueOf(long1);
        BigInteger number2 = BigInteger.valueOf(long2);

        return lcm(number1, number2).longValue();
    }

    public static BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }


    public static double shoelaceArea(List<Coord> v) {
        int n = v.size();
        double a = 0.0;
        for (int i = 0; i < n - 1; i++) {
            a += v.get(i).getX() * v.get(i + 1).getY() - v.get(i + 1).getX() * v.get(i).getY();
        }
        return Math.abs(a + v.get(n - 1).getX() * v.get(0).getY() - v.get(0).getX() * v.get(n - 1).getY()) / 2.0;
    }

    public static BigInteger shoelaceAreaFromRange(List<CoordRange> v) {

        BigInteger a = BigInteger.ZERO;

        Coord lastCoord = null;

        for(CoordRange range : v){

            if(lastCoord != null){
                a = a.add(BigInteger.valueOf(lastCoord.getX() * range.start().getY() - range.start().getX() * lastCoord.getY()));
            }

            boolean rangeIsVertical = Objects.equals(range.start().getX(), range.end().getX());

            if(rangeIsVertical){
                long x = range.start().getX();

                long startY = range.start().getY();
                long endY = range.end().getY();

                long dY = startY - endY;

                a= a.subtract(BigInteger.valueOf(x).multiply(BigInteger.valueOf(dY)));
            }
            else{
                long y = range.start().getY();
                long startX = range.start().getX();
                long endX = range.end().getX();

                long dX = startX - endX;

                a= a.add(BigInteger.valueOf(y).multiply(BigInteger.valueOf(dX)));
            }
                lastCoord = range.end();
        }

        Coord firstCoord = v.get(0).direction() == Direction.R || v.get(0).direction() == Direction.U? v.get(0).start(): v.get(0).end();

        return a
                .add(BigInteger.valueOf(lastCoord.getX() * firstCoord.getY()
                        - firstCoord.getX() * lastCoord.getY())
                ).abs().divide(BigInteger.TWO);
    }

}
