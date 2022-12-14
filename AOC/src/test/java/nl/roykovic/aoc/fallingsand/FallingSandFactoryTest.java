package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.utils.Coord;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FallingSandFactoryTest {
    @Test
    void testMaxExampleNumberOfFallingSand() throws IOException {
        File input = new File("src/test/resources/FallingSandTestInput.txt");
        Cave cave = new FallingSandFactory().generateFromFile(input);

        boolean intoTheAbyss = false;

        int abyssY = cave.highestY;

        while(!intoTheAbyss) {
            Sand sand = new Sand(new Coord(500,0));
            cave.particles.put(new Coord(500,0), sand);
            boolean movable = true;
            while(movable){
                movable = sand.move(cave.particles, abyssY +2);

                if(sand.getCoord().getY() == abyssY){
                    intoTheAbyss = true;
                    movable = false;
                }
            };
        }

        assertEquals(24, cave.particles.values().stream().filter(it -> it instanceof Sand).count()-1);
    }

    @Test
    void testMaxActualNumberOfFallingSand() throws IOException {
        File input = new ClassPathResource("FallingSandInput.txt").getFile();
        Cave cave = new FallingSandFactory().generateFromFile(input);

        boolean intoTheAbyss = false;

        int abyssY = cave.highestY;

        while(!intoTheAbyss) {
            Sand sand = new Sand(new Coord(500,0));
            cave.particles.put(new Coord(500,0), sand);
            boolean movable = true;
            while(movable){
                movable = sand.move(cave.particles, abyssY +2);

                if(sand.getCoord().getY() == abyssY){
                    intoTheAbyss = true;
                    movable = false;
                }
            };
        }

        assertEquals(858, cave.particles.values().stream().filter(it -> it instanceof Sand).count()-1);
    }

    @Test
    void testMaxExampleNumberOfFallingSandWithFloor() throws IOException {
        File input = new File("src/test/resources/FallingSandTestInput.txt");
        Cave cave = new FallingSandFactory().generateFromFile(input);

        int abyssY = cave.highestY;

        while(true) {
            Sand sand = new Sand(new Coord(500,0));
            cave.particles.put(new Coord(500,0), sand);
            boolean movable = true;
            while(movable){
                movable = sand.move(cave.particles, abyssY +2);
            };
            if(sand.getCoord().equals(new Coord(500,0))){
                break;
            }
        }

        assertEquals(93, cave.particles.values().stream().filter(it -> it instanceof Sand).count());
    }

    @Test
    void testMaxActualNumberOfFallingSandWithFloor() throws IOException {
        File input = new ClassPathResource("FallingSandInput.txt").getFile();
        Cave cave = new FallingSandFactory().generateFromFile(input);

        int abyssY = cave.highestY;

        while(true) {
            Sand sand = new Sand(new Coord(500,0));
            cave.particles.put(new Coord(500,0), sand);
            boolean movable = true;
            while(movable){
                movable = sand.move(cave.particles, abyssY +2);
            };
            if(sand.getCoord().equals(new Coord(500,0))){
                break;
            }
        }

        assertEquals(26845, cave.particles.values().stream().filter(it -> it instanceof Sand).count());
    }
}
