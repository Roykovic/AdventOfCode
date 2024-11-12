package nl.roykovic.aoc._2016.day2_passcode;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.List;
import java.util.stream.Stream;
public class PasscodeFactory {
    public String generate(Stream<String> input, List<Coord> passCodeCoords) {
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
            int valueToAdd = passCodeCoords.indexOf(new Coord(curCoord));
            String stringValueToAdd = String.valueOf(valueToAdd);
            if(valueToAdd > 9){
                stringValueToAdd = String.valueOf((char)(valueToAdd-9 + 64));
            }
            passcode.append(stringValueToAdd);
        }

        return passcode.toString();
    }
}
