package nl.roykovic.aoc._2016.day4_realrooms;

import nl.roykovic.aoc._2015.day5_naughtystrings.NaughtyOrNiceFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Room {

    private int id;
    private String name;
    private String checksum;

    public Room(int id, String name, String checksum) {
        this.id = id;
        this.name = name;
        this.checksum = checksum;
    }

    private String calculateChecksum(){

        Set<Character> uniqueChars = new HashSet<>();

        for(char c : name.toCharArray()){
            if(c != '-') {
                uniqueChars.add(c);
            }
        }

        Map<Character, Integer> occurences =  uniqueChars.stream().collect(Collectors.toMap(it -> it, it -> StringUtils.countMatches(name, it)));


        Comparator<Character> comparator = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                if(!occurences.get(o1).equals(occurences.get(o2))){
                    return occurences.get(o2).compareTo(occurences.get(o1));
                }
                else {
                    return o1.compareTo(o2);
                }
            }
        };

        List<Character> characterList = new ArrayList<>(uniqueChars.stream().toList());

        characterList.sort(comparator);

        return "[" + characterList.subList(0,5).stream().map(String::valueOf).collect(Collectors.joining()) + "]";
    }

    public String decipherName(){
        int rotations = id % 26;

        String decipheredName = "";

        for(char c : name.toCharArray()){

            if(c == '-'){
                decipheredName += ' ';
            }
            else {
                int i = c + rotations;

                if (i > 122) {
                    i -= 26;
                }

                decipheredName += (char) i;
            }
        }


        return decipheredName;
    }

    public boolean isValid(){
        var hans = calculateChecksum();
        return calculateChecksum().equals(checksum);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
