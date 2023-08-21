package nl.roykovic.aoc._2015.day14_reindeerrace;

import nl.roykovic.aoc._2015.day13_tablehappiness.TableHappinessFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReindeerFactoryTest {

    @Test
    void testActualFastestReindeer() throws IOException {
        File input = new ClassPathResource("2015/ReindeerInput.txt").getFile();
        List<Reindeer> reindeer = new ReindeerFactory().generateFromFile(input);

        int raceTime = 2503;

        List<Integer> distancesTraveled = new ArrayList<>();

        for(Reindeer deer: reindeer){
            distancesTraveled.add(deer.getDistanceTraveledInTime(raceTime));
        }

        assertEquals(2660, Collections.max(distancesTraveled));
    }
}
