package nl.roykovic.aoc._2022.fallingsand;

import nl.roykovic.aoc.utils.Coord;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FallingSandFactoryTest {
    @Test
    void testMaxExampleNumberOfFallingSand() throws IOException {
        File input = new File("src/test/resources/2022/FallingSandTestInput.txt");
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

        assertEquals(24, cave.particles().values().stream().filter(it -> it instanceof Sand).count()-1);
    }

    @Test
    void testMaxActualNumberOfFallingSand() throws IOException {
        File input = new ClassPathResource("2022/FallingSandInput.txt").getFile();
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

        assertEquals(858, cave.particles().values().stream().filter(it -> it instanceof Sand).count()-1);
    }

    @Test
    void testMaxExampleNumberOfFallingSandWithFloor() throws IOException {
        File input = new File("src/test/resources/2022/FallingSandTestInput.txt");
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

        assertEquals(93, cave.particles().values().stream().filter(it -> it instanceof Sand).count());
    }

    @Test
    void testMaxActualNumberOfFallingSandWithFloor() throws IOException {
        File input = new ClassPathResource("2022/FallingSandInput.txt").getFile();
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

        assertEquals(26845, cave.particles().values().stream().filter(it -> it instanceof Sand).count());
    }
}
