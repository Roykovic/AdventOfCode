package nl.roykovic.aoc._2022.supplystacks;

import java.util.ArrayList;
import java.util.List;

public class CrateStack {

    private List<Character> crates = new ArrayList<>();

    public CrateStack(){
    }

    public Character pop(){
        return crates.remove(crates.size() -1);
    }

    public List<Character> pop(int amount){

        List<Character> returnList = new ArrayList<>(crates.subList(crates.size()-(amount), crates.size()));
        crates = crates.subList(0, crates.size()-amount);
        return returnList;
    }

    public void push(Character crate){
        crates.add(crate);
    }

    public void push(List<Character> newCrates){
        crates.addAll(newCrates);
    }
}
