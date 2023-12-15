package nl.roykovic.aoc.utils;

import java.math.BigInteger;
import java.util.List;

public class Utils {

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

}
