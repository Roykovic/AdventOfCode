package nl.roykovic.aoc._2023.day16_beam.mirrors;

import nl.roykovic.aoc.utils.Coord;

public class MirrorFactory {
    public static Mirror getMirrorForChar(char c, Coord coord){

        return switch (c) {
            case '\\' -> new SlantedLeftMirror(coord);
            case '/' -> new SlantedRightMirror(coord);
            case '-' -> new HorizontalSplitter(coord);
            case '|' -> new VerticalSplitter(coord);
            default -> new Mirror('.',coord);
        };
    }
}
