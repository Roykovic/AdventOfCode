package nl.roykovic.aoc._2015.day4_adventcoin;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {

    public static String encode(String input) throws NoSuchAlgorithmException {
        return StringUtils.leftPad
                (new BigInteger(1,
                                MessageDigest.getInstance("MD5")
                                        .digest(input.getBytes(StandardCharsets.UTF_8)))
                                .toString(16),
                        32,
                        '0');
    }
}
