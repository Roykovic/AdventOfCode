package nl.roykovic.aoc._2015.day19_molecules;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MoleculeFactory {
    public int generateFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = reader.lines().toList();

        String molecule = lines.get(lines.size() -1);

        Map<String, List<String>> mutations = new HashMap<>();

        for(String line : lines){
            if(StringUtils.isBlank(line)){
                break;
            }
            String[] parts = StringUtils.splitByWholeSeparator(line, " => ");

            if(!mutations.containsKey(parts[0])){
                mutations.put(parts[0], new ArrayList<>());
            }
            mutations.get(parts[0]).add(parts[1]);
        }
        return calibrate(molecule, mutations).size();
    }

    private HashSet<String> calibrate(String molecule, Map<String, List<String>> mutations) {
        HashSet<String> possibleMolecules = new HashSet<>();


        for (var entry : mutations.entrySet()) {
            for (String mutation : entry.getValue()) {
                int lastIndex = 0;
                while (lastIndex != -1) {

                    lastIndex = molecule.indexOf(entry.getKey(), lastIndex);

                    if (lastIndex != -1) {
                        possibleMolecules.add(molecule.substring(0,lastIndex) + mutation + molecule.substring(lastIndex +1));

                        lastIndex++;
                    }
                }
            }
        }

        return possibleMolecules;
    }
}
