package nl.roykovic.aoc._2016.day5_hashpassword;

import nl.roykovic.aoc._2015.day4_adventcoin.MD5Encoder;

import java.util.Arrays;
import java.util.stream.Collectors;
public class HashpasswordFactory {
    public String generate(String input) {
        long padding = 0L;

        String hash = "";

        StringBuilder pass = new StringBuilder();

        while(pass.length() < 8) {
            while (!hash.startsWith("00000")) {
                hash = MD5Encoder.encode(input + padding);
                padding++;
            }
            pass.append(hash.charAt(5));
            hash = MD5Encoder.encode(input + padding);
        }


        return pass.toString();
    }

    public String generateWithPosition(String input){
        long padding = 0L;

        String hash = "";

        Character[] pass = new Character[8];

        while(Arrays.stream(pass).toList().contains(null)) {
            while (!hash.startsWith("00000")) {
                hash = MD5Encoder.encode(input + padding);
                padding++;
            }

            int pos = (hash.charAt(5) - '0');

            if(pos < pass.length && pass[pos] == null){
                pass[pos] = hash.charAt(6);
            }

            hash = MD5Encoder.encode(input + padding);
        }


        return Arrays.stream(pass).map(String::valueOf).collect(Collectors.joining());
    }
}
