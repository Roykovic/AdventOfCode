package nl.roykovic.aoc._2022.beacon;

import nl.roykovic.aoc.utils.Coord;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SensorFactoryTest {
    @Test
    void testExampleLineNotBeaconIndices() throws IOException {
        File input = new File("src/test/resources/2022/BeaconTestInput.txt");
        List<Sensor> list = new SensorFactory().generateFromFile(input);

        long yToCheck = 10;

        Set<Coord> notBeaconIndicesOnY = new HashSet<>();

        for(Sensor sensor : list){
            long manhattanDistanceToY = sensor.getCoord().manhattanDistance(new Coord(sensor.getCoord().getX(), yToCheck));

            long dDistance = sensor.manhattanDistanceToBeacon() - manhattanDistanceToY;

            for(long i = -dDistance; i < dDistance; i++){

                Coord coord = new Coord(sensor.getCoord().getX() - i, yToCheck);
                if(sensor.getClosestBeacon().getCoord() != coord) {
                    notBeaconIndicesOnY.add(coord);
                }
            }
        }

        assertEquals(26, notBeaconIndicesOnY.size());

    }

    @Test
    void testActualLineNotBeaconIndices() throws IOException {
        File input = new ClassPathResource("2022/BeaconInput.txt").getFile();
        List<Sensor> list = new SensorFactory().generateFromFile(input);

        long yToCheck = 2000000;

        Set<Coord> notBeaconIndicesOnY = new HashSet<>();

        for(Sensor sensor : list){
            long manhattanDistanceToY = sensor.getCoord().manhattanDistance(new Coord(sensor.getCoord().getX(), yToCheck)); //check the manhattan distance to the y coord

            long dDistance = sensor.manhattanDistanceToBeacon() - manhattanDistanceToY; //the dDistance is the distance the beacon can still be away -> manhattan distance is just the dX + dY in absolute numbers.
                                                                                        // so if the closest y coord is 10 away, and the beacon is 12 away.
                                                                                        // That means that left and right from the closest y coord 2 (12-10=2) indices cannot contain beacons

            for(long i = -dDistance; i < dDistance; i++){

                Coord coord = new Coord(sensor.getCoord().getX() - i, yToCheck);    //check these possible coords, from -dDistance, y to dDistance,y
                if(sensor.getClosestBeacon().getCoord() != coord) {
                    notBeaconIndicesOnY.add(coord);                                     //if the coord does not contain your beacon, add it to the list
                }
            }
        }
        assertEquals(5181556, notBeaconIndicesOnY.size());
    }

    @Test
    void testExampleTuningFrequency() throws IOException {
        File input = new File("src/test/resources/2022/BeaconTestInput.txt");
        List<Sensor> list = new SensorFactory().generateFromFile(input);

    }
}
