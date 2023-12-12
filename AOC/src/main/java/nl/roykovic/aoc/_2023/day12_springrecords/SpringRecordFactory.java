package nl.roykovic.aoc._2023.day12_springrecords;

import java.util.Arrays;
import java.util.stream.Stream;

public class SpringRecordFactory {
    public int generate(Stream<String> input){
        return 0;
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
        else if(input.startsWith("#")){

            int groupEnd = !input.contains(".") ? input.length() : input.indexOf(".");


            if(input.substring(0, groupEnd).length() == configuration[0]){
                System.out.println(input + " fits " + configuration[0]);
                System.out.println("now checking in " + input.substring(configuration[0]) + " for " + Arrays.toString(Arrays.copyOfRange(configuration, 1, configuration.length)));
                System.out.println();
                return calculateWays(input.substring(configuration[0]), Arrays.copyOfRange(configuration, 1, configuration.length));
            }
            if(input.substring(0,groupEnd).length() > configuration[0]){
                if(input.charAt(configuration[0])=='?') {
                    return calculateWays("." + input.substring(configuration[0] + 1), Arrays.copyOfRange(configuration, 1, configuration.length));
                }
            }
            return 0;
        }
        else{
            throw new RuntimeException();
        }
    }
}
