package nl.roykovic.aoc._2016.day17_vault;

import nl.roykovic.aoc._2022.day12_hillclimb.Node;
import nl.roykovic.aoc.utils.BreadthFirstSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class VaultFactory {

    private String passcode = "";
    public int generate(String input) {

        passcode = input;

        List<Node> nodes = new ArrayList<>();

        for( int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
                nodes.add(new VaultNode(x,y, this));
            }
        }

        var hans = BreadthFirstSearch.searchCircular(
                nodes.stream().filter(it -> it.getX() == 0 && it.getY() == 0).findFirst().orElseThrow(),
                nodes.stream().filter(it -> it.getX() == 3 && it.getY() == 3).findFirst().orElseThrow()
        );

        return 0;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
