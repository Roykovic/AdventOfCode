package nl.roykovic.aoc._2023.day12_springrecords;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class SpringRecordFactory {

    Map<Integer, Integer> cache = new ConcurrentHashMap<>();
    public int generate(Stream<String> input, boolean unfold){
        return input
                .map(it -> it.split(" "))
                .map(it -> {
                    if(unfold){
                        it = this.unfold(it);
                    }
                    return it;
                })
                .mapToInt(it ->
                        calculateWays(
                                it[0],
                                Arrays.stream(
                                        it[1]
                                                .split(","))
                                        .mapToInt(Integer::parseInt)
                                        .toArray())).
                sum();
    }

    public int calculateWays(String input, int[] configuration){
        if(configuration.length == 0){
            return input.contains("#")? 0:1;
        }
        if(input.isEmpty()){
            return 0;
        }

        if(input.startsWith(".")){
            return calculateWays(input.substring(1), configuration);
        } else if (input.startsWith("?")) {
            return calculateWays("#"+input.substring(1),configuration) + calculateWays("."+input.substring(1),configuration);
        }
        else{
            int groupEnd = !input.contains(".") ? input.length() : input.indexOf(".");

            if(input.substring(0, groupEnd).length() == configuration[0]){
                return calculateWays(input.substring(configuration[0]), Arrays.copyOfRange(configuration, 1, configuration.length));
            }
            if(input.substring(0,groupEnd).length() > configuration[0]){
                if(input.charAt(configuration[0])=='?') {
                    return calculateWays("." + input.substring(configuration[0] + 1), Arrays.copyOfRange(configuration, 1, configuration.length));
                }
            }
            return 0;
        }
    }

    private String[] unfold(String[] in){

        String input = in[0];
        String configuration = in[1];

        String unfoldedInput = String.join("?", Collections.nCopies(5,input));
        String unfoldedConfiguration = String.join(",", Collections.nCopies(5,configuration));

        return new String[]{unfoldedInput, unfoldedConfiguration};
    }
}
