package nl.roykovic.aoc._2022.day20_encryptednumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class EncryptedNumbersFactory {
    public EncryptedNumbersList generateFromFile(File file, int key) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        EncryptedNumbersList list = new EncryptedNumbersList();

        for (String line : lines) {
            list.addToList(new EncryptedNumber(Long.parseLong(line) * key));
        }

        return list;
    }
}
