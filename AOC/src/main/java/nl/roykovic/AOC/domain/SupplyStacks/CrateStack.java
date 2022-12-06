package nl.roykovic.AOC.domain.SupplyStacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrateStack {

    private final List<Character> crates = new ArrayList<>();

    public CrateStack(){
    }

    public Character pop(){
        return crates.remove(crates.size() -1);
    }

    public void push(Character crate){
        crates.add(crate);
    }
}
