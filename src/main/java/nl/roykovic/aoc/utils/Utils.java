package nl.roykovic.aoc.utils;

import java.math.BigInteger;

public class Utils {

    public static Long lcm(Long long1, Long long2) {
        BigInteger number1 = BigInteger.valueOf(long1);
        BigInteger number2 = BigInteger.valueOf(long2);

        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd).longValue();
    }

    public static BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }
}
