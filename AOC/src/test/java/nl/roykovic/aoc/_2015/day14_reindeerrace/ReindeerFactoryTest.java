package nl.roykovic.aoc._2015.day14_reindeerrace;

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

    @Test
    void testActualHighestScoringReindeer() throws IOException {
        File input = new ClassPathResource("2015/ReindeerInput.txt").getFile();
        List<Reindeer> reindeer = new ReindeerFactory().generateFromFile(input);

        int raceTime = 2503;

        for(int i = 1 ; i <= raceTime; i++) {
            int highestDistanceTraveled = 0;
            List<Reindeer> highestDistanceReindeer = new ArrayList<>();
            for (Reindeer deer : reindeer) {
                int distanceTraveled = deer.getDistanceTraveledInTime(i);
                if(distanceTraveled == highestDistanceTraveled){
                    highestDistanceReindeer.add(deer);
                }
                else if(distanceTraveled > highestDistanceTraveled){
                    highestDistanceReindeer = new ArrayList<>(List.of(deer));
                    highestDistanceTraveled = distanceTraveled;
                }
            }
            highestDistanceReindeer.forEach(Reindeer::giveScore);
        }
        int maxScore = reindeer.stream().map(Reindeer::getScore).mapToInt(it -> it).max().orElseThrow();

        assertEquals(1256, maxScore);
    }
}
