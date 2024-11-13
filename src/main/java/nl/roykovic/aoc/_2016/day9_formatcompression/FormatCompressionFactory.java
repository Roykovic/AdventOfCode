package nl.roykovic.aoc._2016.day9_formatcompression;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;
public class FormatCompressionFactory {
    public int generate(Stream<String> input) {
        return 0;
    }

    public String decompress(String input){
        if(!input.contains("(")){
            return input;
        }
        String result = "";

        while(input.contains("(")) {
            result += StringUtils.substringBefore(input, "(");
            String operator = StringUtils.substringBetween(input, "(", ")");
            int numberOfChars = Integer.parseInt(StringUtils.substringBefore(operator, "x"));
            int repetitions = Integer.parseInt(StringUtils.substringAfter(operator, "x"));

            input = StringUtils.substringAfter(input, ")");
            for(int i = 0; i< repetitions; i++){
                result += input.substring(0, numberOfChars);
            }
            input = input.substring(numberOfChars);
        }

        return result + input;
    }
}
