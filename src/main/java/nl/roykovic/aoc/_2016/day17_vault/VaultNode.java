package nl.roykovic.aoc._2016.day17_vault;

import nl.roykovic.aoc._2015.day4_adventcoin.MD5Encoder;
import nl.roykovic.aoc._2022.day12_hillclimb.Node;
import nl.roykovic.aoc.utils.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaultNode extends Node {
    private final VaultFactory factory;
    List<Character> openChars = List.of('b', 'c', 'd', 'e', 'f');
    public VaultNode(int x, int y, VaultFactory factory) {
        super(x, y);
        this.factory = factory;
    }

    @Override
    public Map<Node, Integer> getAdjacentNodes() {

        var encoded = MD5Encoder.encode(factory.getPasscode());

        Map<Node, Integer> neighbours = new HashMap<>();

        if(openChars.contains(encoded.charAt(0))){
            neighbours.put(new Node(this.getCoord().moveAndGet(Direction.U)), 0);
        }
        if(openChars.contains(encoded.charAt(1))){
            neighbours.put(new Node(this.getCoord().moveAndGet(Direction.D)), 0);
        }
        if(openChars.contains(encoded.charAt(2))){
            neighbours.put(new Node(this.getCoord().moveAndGet(Direction.L)), 0);
        }
        if(openChars.contains(encoded.charAt(3))){
            neighbours.put(new Node(this.getCoord().moveAndGet(Direction.R)), 0);
        }

        return neighbours;
    }
}
