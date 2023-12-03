package nl.roykovic.aoc.utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

public class FileReaderService {
    public static Stream<String> streamLinesFromFile(int year, String filename, boolean testResource){

        String path = "src/" +
                (testResource ? "test/" : "main/") +
                "resources/" +
                year +
                "/" +
                filename;

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return reader.lines();
    }
}
