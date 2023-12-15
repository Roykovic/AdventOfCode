package nl.roykovic.aoc._2022.day14_fallingsand;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FallingSandFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "FallingSandTestInput.txt,true,24",
            "FallingSandInput.txt,false,858"
    })
    void testMaxNumberOfFallingSand(String filename, boolean test, long expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        Cave cave = new FallingSandFactory().generateFromFile(input);

        boolean intoTheAbyss = false;

        Long abyssY = cave.highestY();

        while(!intoTheAbyss) {
            Sand sand = new Sand(new Coord(500L,0L));
            cave.particles().put(new Coord(500L,0L), sand);
            boolean movable = true;
            while(movable){
                movable = sand.move(cave.particles(), abyssY +2L);

                if(Objects.equals(sand.coord().getY(), abyssY)){
                    intoTheAbyss = true;
                    movable = false;
                }
            }
        }

        assertEquals(expected, cave.particles().values().stream().filter(it -> it instanceof Sand).count()-1);
    }

    @ParameterizedTest
    @CsvSource({
            "FallingSandTestInput.txt,true,93",
            "FallingSandInput.txt,false,26845"
    })
    void testMaxNumberOfFallingSandWithFloor(String filename, boolean test, long expected){
        var input = FileReaderService.streamLinesFromFile(2022, filename, test);
        Cave cave = new FallingSandFactory().generateFromFile(input);

        Long abyssY = cave.highestY();

        while(true) {
            Sand sand = new Sand(new Coord(500L,0L));
            cave.particles().put(new Coord(500L,0L), sand);
            boolean movable = true;
            while(movable){
                movable = sand.move(cave.particles(), abyssY +2);
            }
            if(sand.coord().equals(new Coord(500L,0L))){
                break;
            }
        }

        assertEquals(expected, cave.particles().values().stream().filter(it -> it instanceof Sand).count());
    }
}
