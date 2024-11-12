package nl.roykovic.aoc._2016.day2_passcode;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.List;
import java.util.stream.Stream;
public class PasscodeFactory {

    List<Coord> passCodeCoords = List.of(
            new Coord(100000, 1000), //stuffing to make it 1-indexed so the indexes are the same as the number on the pad
            new Coord(0,0),
            new Coord(1,0),
            new Coord(2,0),
            new Coord(0,1),
            new Coord(1,1),
            new Coord(2,1),
            new Coord(0,2),
            new Coord(1,2),
            new Coord(2,2)
            );

    public int generate(Stream<String> input) {
        var digitInstructions = input.map(String::toCharArray).toList();

        StringBuilder passcode = new StringBuilder();

        Coord curCoord = new Coord(passCodeCoords.get(5));

        for(var i : digitInstructions){
            for(char c : i){
                Direction direction = Direction.valueOf(String.valueOf(c));
                if(passCodeCoords.contains(curCoord.moveAndGet(direction))){
                    curCoord.move(direction);
                }
            }
            passcode.append(passCodeCoords.indexOf(new Coord(curCoord)));
        }

        return Integer.parseInt(passcode.toString());
    }
}
