package nl.roykovic.aoc._2022.day20_encryptednumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EncryptedNumbersFactory {
    public EncryptedNumbersList generateFromFile(Stream<String> lines, int key){
        return new EncryptedNumbersList(lines.map(it -> new EncryptedNumber(Long.parseLong(it) * key)).collect(Collectors.toList()));
    }
}
