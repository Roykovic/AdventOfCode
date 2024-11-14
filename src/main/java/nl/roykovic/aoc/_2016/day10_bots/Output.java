package nl.roykovic.aoc._2016.day10_bots;

import java.util.ArrayList;
import java.util.List;

public class Output implements Receiver{
    List<Integer> chips = new ArrayList<>();
    int number;

    public Output(int number) {
        this.number = number;
    }

    @Override
    public void accept(int chip) {
        chips.add(chip);
    }
}
