package nl.roykovic.aoc._2023.day12_springrecords;

import nl.roykovic.aoc.utils.Memoizer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SpringRecordFactory {
    public Long generate(Stream<String> input, boolean unfold){
        return input
                .map(it -> it.split(" "))
                .map(it -> {
                    if(unfold){
                        it = this.unfold(it);
                    }
                    return it;
                })
                .mapToLong(it ->
                        calculateWays(
                                it[0],
                                Arrays.stream(
                                        it[1]
                                                .split(","))
                                        .mapToInt(Integer::parseInt)
                                        .boxed()
                                        .toList())).
                sum();
    }
    Function<String, Function<List<Integer>, Long>> cacheculateWays =
            Memoizer.memoize(x -> Memoizer.memoize(y -> calculateWays(x,y)));

    public Long calculateWays(String input, List<Integer> configuration){
        if(configuration.isEmpty()){
            return input.contains("#")? 0L:1L;
        }
        if(input.isEmpty()){
            return 0L;
        }

        if(input.startsWith(".")){
            return cacheculateWays.apply(input.substring(1)).apply(configuration);
        } else if (input.startsWith("?")) {
            return cacheculateWays.apply("#"+input.substring(1)).apply(configuration) + cacheculateWays.apply("."+input.substring(1)).apply(configuration);
        }
        else{
            int groupEnd = !input.contains(".") ? input.length() : input.indexOf(".");

            if(input.substring(0, groupEnd).length() == configuration.get(0)){
                return cacheculateWays.apply(input.substring(configuration.get(0))).apply(configuration.subList(1, configuration.size()));
            }
            if(input.substring(0,groupEnd).length() > configuration.get(0)){
                if(input.charAt(configuration.get(0))=='?') {
                    return cacheculateWays.apply("." + input.substring(configuration.get(0) + 1)).apply(configuration.subList(1, configuration.size()));
                }
            }
            return 0L;
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
