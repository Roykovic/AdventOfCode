package nl.roykovic.aoc._2015.day19_molecules;

import nl.roykovic.aoc._2022.day12_hillclimb.Node;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MoleculeFactory {

    private List<String> triedMolecules = new ArrayList<>();
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

    public int generateNodesFromFile(List<String> lines){
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

        List<Integer> results = new ArrayList<>();
        fabricate(results, "e", molecule,mutations,0);
        return results.stream().mapToInt(it -> it).min().orElseThrow();
    }

    private HashSet<String> calibrate(String molecule, Map<String, List<String>> mutations) {
        HashSet<String> possibleMolecules = new HashSet<>();


        for (var entry : mutations.entrySet()) {
            for (String mutation : entry.getValue()) {
                int lastIndex = 0;
                while (lastIndex != -1) {

                    lastIndex = molecule.indexOf(entry.getKey(), lastIndex);

                    if (lastIndex != -1) {
                        possibleMolecules.add(molecule.substring(0,lastIndex) + mutation + molecule.substring(lastIndex +entry.getKey().length()));

                        lastIndex++;
                    }
                }
            }
        }

        return possibleMolecules;
    }

    private void fabricate(List<Integer> results, String molecule, String expected, Map<String, List<String>> mutations, int step) {
        if(Objects.equals(molecule, expected)){
            results.add(step);
        }

        for (var entry : mutations.entrySet()) {
            for (String mutation : entry.getValue()) {
                int lastIndex = 0;
                while (lastIndex != -1) {

                    lastIndex = molecule.indexOf(entry.getKey(), lastIndex);

                    if (lastIndex != -1) {
                        String newMolecule = molecule.substring(0,lastIndex) + mutation + molecule.substring(lastIndex +entry.getKey().length());

                       if(newMolecule.length() <= expected.length() && !triedMolecules.contains(newMolecule)) {
                           triedMolecules.add(newMolecule);
                           fabricate(results, newMolecule, expected, mutations, step + 1);
                       }
                        lastIndex++;
                    }
                }
            }
        }
    }
}
