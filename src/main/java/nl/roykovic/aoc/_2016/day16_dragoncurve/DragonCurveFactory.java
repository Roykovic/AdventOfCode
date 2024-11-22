package nl.roykovic.aoc._2016.day16_dragoncurve;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;
public class DragonCurveFactory {
    public String generate(String input, int diskSize) {
        while(input.length() < diskSize){
            input = dragonCurve(input);
        }

        String generated = input.substring(0, diskSize);
        StringBuilder checksum = new StringBuilder();
        while(checksum.isEmpty() || checksum.length()%2 ==0) {
            checksum = new StringBuilder();
            for (int i = 0; i < generated.length() - 1; i+=2) {
                char curChar = generated.charAt(i);
                char nextChar = generated.charAt(i + 1);

                if (curChar == nextChar) {
                    checksum.append('1');
                } else {
                    checksum.append('0');
                }
            }
            generated = String.valueOf(checksum);
        }

        return checksum.toString();
    }


    private String dragonCurve(String a){
        String b = a;

        b = StringUtils.reverse(b);

        StringBuilder newB = new StringBuilder();
        for(char c : b.toCharArray()){
            if(c == '0'){
                newB.append('1');
            }
            else{
                newB.append('0');
            }
        }

        return a + "0" +newB.toString();
    }
}
