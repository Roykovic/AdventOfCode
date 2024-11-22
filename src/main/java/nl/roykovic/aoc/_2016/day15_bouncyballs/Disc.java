package nl.roykovic.aoc._2016.day15_bouncyballs;

import java.util.stream.IntStream;

public class Disc {
    int[] positions;
    int startPosition;

    public Disc(int positionsLength, int startPosition) {
        this.positions = IntStream.range(0, positionsLength).toArray();
        this.startPosition = startPosition;
    }

    public boolean isOpen(int time){
        int index = startPosition + time;

        if(index >= positions.length){
            index = index%positions.length;
        }

        return positions[index] == 0;
    }
}
