package nl.roykovic.aoc.utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Stream;

public class FileReaderService {
    public static Stream<String> streamLinesFromFile(int year, String filename, boolean testResource){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(buildPath(year, filename, testResource)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return reader.lines();
    }

    public static List<String> getLinesFromFile(int year, String filename, boolean testResource){
        return streamLinesFromFile(year, filename, testResource).toList();
    }

    public static String getFirstLineFromFile(int year, String filename, boolean testResource){
        return streamLinesFromFile(year, filename, testResource).findFirst().orElseThrow();
    private static String buildPath(int year, String filename, boolean testResource){
        return "src/" +
                (testResource ? "test/" : "main/") +
                "resources/" +
                year +
                "/" +
                filename;
    }
}
