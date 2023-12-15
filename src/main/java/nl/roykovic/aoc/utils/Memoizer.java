package nl.roykovic.aoc.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memoizer<T, U> {
//https://dzone.com/articles/java-8-automatic-memoization
    private final Map<T, U> cache = new ConcurrentHashMap<>();

    private Memoizer() {}

    private Function<T, U> doMemoize(final Function<T, U> function) {
        return input -> cache.computeIfAbsent(input, function);
    }

    public static <T, U> Function<T, U> memoize(final Function<T, U> function) {
        return new Memoizer<T, U>().doMemoize(function);
    }
}