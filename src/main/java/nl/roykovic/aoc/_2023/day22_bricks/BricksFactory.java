package nl.roykovic.aoc._2023.day22_bricks;

import nl.roykovic.aoc._2023.day18_lagoon.CoordRange;
import nl.roykovic.aoc.utils.Coord;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BricksFactory {
    public int generate(Stream<String> input) {
        var ranges = input.map(it -> Arrays.stream(it.split("~")).map(Coord::new).toArray())
                .map(it -> new CoordRange((Coord) it[0], (Coord) it[1], null))
                .sorted(Comparator.comparingLong(it -> Math.min(it.start().getZ(), it.end().getZ())))
                .toList();
//        drawBricks(ranges);
        for (int i = 0; i < ranges.size(); i++) {
            CoordRange range = ranges.get(i);
            boolean collides = false;
            while (range.end().getZ()>0 && !collides) {
                range.moveDown();
                for(var r: ranges.stream().filter(it -> !it.equals(range)).toList()){
                    if(r.collidesWith(range)){
                        r.moveUp();
                        collides=true;
                        break;
                    }
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        drawBricks(ranges);
        return 0;
    }

    public void drawBricks(List<CoordRange> bricks) {
        long maxX = bricks.stream().mapToLong(it -> Math.max(it.start().getX(), it.end().getX())).max().orElseThrow();
        long maxY = bricks.stream().mapToLong(it -> Math.max(it.start().getY(), it.end().getY())).max().orElseThrow();
        long maxZ = bricks.stream().mapToLong(it -> Math.max(it.start().getZ(), it.end().getZ())).max().orElseThrow();

        System.out.println(" x ");
        System.out.println("012");

        for (long z = maxZ; z >= 0; z--) {
            for (long x = 0; x < maxX + 1; x++) {
                var found = false;
                for (int i = 0; i < bricks.size(); i++) {
                    var currentRange = bricks.get(i);
                    var start = new Coord(x, currentRange.start().getY(), z);
                    var end = new Coord(x, currentRange.end().getY(), z);

                    if (currentRange.collidesWith(new CoordRange(start, end, null))) {
                        System.out.print(String.valueOf((char)(i + 65)));
                        found=true;
                        break;
                    }
                }
                if(!found){
                    System.out.printf(z==0?"-":".");
                }

            }
            System.out.print(" " +z);
            System.out.println(z==Math.ceil((double) maxZ /2)?" z":"");
        }

    }
}
