package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.utils.Coord;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FallingSandFactoryTest {
    @Test
    void testMaxExampleNumberOfFallingSand() throws IOException {
        File input = new File("src/test/resources/FallingSandTestInput.txt");
        List<IParticle> list = new FallingSandFactory().generateFromFile(input);

        boolean intoTheAbyss = false;

        int abyssY = list.stream().mapToInt(it -> it.getCoord().getY()).max().orElseThrow(RuntimeException::new);

        while(!intoTheAbyss) {
            Sand sand = new Sand(new Coord(500,0));
            list.add(sand);
            boolean movable = true;
            while(movable){
                movable = sand.move(list, abyssY +1);

                if(sand.getCoord().getY() > abyssY){
                    intoTheAbyss = true;
                    movable = false;
                }
            };
        }

        assertEquals(24, list.stream().filter(it -> it instanceof Sand).count()-1);
    }

    @Test
    void testMaxActualNumberOfFallingSand() throws IOException {
        File input = new ClassPathResource("FallingSandInput.txt").getFile();
        List<IParticle> list = new FallingSandFactory().generateFromFile(input);

        boolean intoTheAbyss = false;

        int abyssY = list.stream().mapToInt(it -> it.getCoord().getY()).max().orElseThrow(RuntimeException::new);

        while(!intoTheAbyss) {
            Sand sand = new Sand(new Coord(500,0));
            list.add(sand);
            boolean movable = true;
            while(movable){
                movable = sand.move(list, abyssY +1);

                if(sand.getCoord().getY() > abyssY){
                    intoTheAbyss = true;
                    movable = false;
                }
            };
        }

        assertEquals(858, list.stream().filter(it -> it instanceof Sand).count()-1);
    }
}
