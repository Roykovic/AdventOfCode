package nl.roykovic.aoc._2022.encryptednumbers;

import nl.roykovic.aoc._2022.lavadroplets.LavaDroplet;
import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Face;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EncryptedNumbersFactory {
    public EncryptedNumbersList generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        EncryptedNumbersList list = new EncryptedNumbersList();

        for(int i =0; i< lines.size(); i++){
            list.addToList(new EncryptedNumber(Long.parseLong(lines.get(i)) * key, i));
        }

        return list;
    }
}
