package nl.roykovic.aoc._2015.wrappingpaper;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WrappingPaperFactory {
    public Stream<IntStream> generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        Stream<String> lines =  reader.lines();
        return lines.map(it -> it.split("x")).map(it -> Arrays.stream(it).mapToInt(NumberUtils::toInt));
    }

    public static int calculateSurfaceBySides(IntStream stream){
        int[] sides = stream.toArray();

        int surface = 0;

        int lw = sides[0] * sides[1];
        int wh = sides[1] * sides[2];
        int hl = sides[2] * sides[0];

        surface += 2*lw;
        surface += 2*wh;
        surface += 2*hl;

        surface += Math.min(Math.min(lw, wh), hl);

        return surface;
    }

    public static int calculateRibbonLengthBySides(IntStream stream){

        int[] streamArray = stream.toArray();
        Supplier<IntStream> sup = () -> Arrays.stream(streamArray);

        int perimeter = sup.get().sorted().limit(2).map(it -> it * 2).sum();
        int volume = sup.get().reduce((a, b) -> a*b).orElseThrow(RuntimeException::new);

        return perimeter + volume;
    }
}
