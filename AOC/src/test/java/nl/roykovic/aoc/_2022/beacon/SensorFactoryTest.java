package nl.roykovic.aoc._2022.beacon;

import nl.roykovic.aoc.utils.Coord;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SensorFactoryTest {
    @Test
    void testExampleLineNotBeaconIndices() throws IOException {
        File input = new File("src/test/resources/2022/BeaconTestInput.txt");
        List<Sensor> list = new SensorFactory().generateFromFile(input);

        long yToCheck = 10;

        List<Coord> notBeaconIndicesOnY = new ArrayList<>();

        for(Sensor sensor : list){
            long manhattanDistanceToY = sensor.getCoord().manhattanDistance(new Coord(sensor.getCoord().getX(), yToCheck));

            long dDistance = sensor.getManhattanDistanceToBeacon() - manhattanDistanceToY;

            for(long i = -dDistance; i < dDistance; i++){

                Coord coord = new Coord(sensor.getCoord().getX() - i, yToCheck);
                if(sensor.getClosestBeacon().coord() != coord) {
                    notBeaconIndicesOnY.add(coord);
                }
            }
        }

        assertEquals(26, notBeaconIndicesOnY.stream().distinct().count());

    }

    @Test
    void testActualLineNotBeaconIndices() throws IOException {
        File input = new ClassPathResource("2022/BeaconInput.txt").getFile();
        List<Sensor> list = new SensorFactory().generateFromFile(input);

        long yToCheck = 2000000;

        List<Coord> notBeaconIndicesOnY = new ArrayList<>();

        for(Sensor sensor : list){
            long manhattanDistanceToY = sensor.getCoord().manhattanDistance(new Coord(sensor.getCoord().getX(), yToCheck)); //check the manhattan distance to the y coord

            long dDistance = sensor.getManhattanDistanceToBeacon() - manhattanDistanceToY; //the dDistance is the distance the beacon can still be away -> manhattan distance is just the dX + dY in absolute numbers.
                                                                                        // so if the closest y coord is 10 away, and the beacon is 12 away.
                                                                                        // That means that left and right from the closest y coord 2 (12-10=2) indices cannot contain beacons

            for(long i = -dDistance; i < dDistance; i++){

                Coord coord = new Coord(sensor.getCoord().getX() - i, yToCheck);    //check these possible coords, from -dDistance, y to dDistance,y
                if(sensor.getClosestBeacon().coord() != coord) {
                    notBeaconIndicesOnY.add(coord);                                     //if the coord does not contain your beacon, add it to the list
                }
            }
        }
        assertEquals(5181556, notBeaconIndicesOnY.stream().distinct().count());
    }

    @Test
    void testExampleTuningFrequency() throws IOException {
        File input = new File("src/test/resources/2022/BeaconTestInput.txt");
        List<Sensor> list = new SensorFactory().generateFromFile(input);

        long maxCoord = 20;

        Coord beaconCoord = null;
        outerLoop: for(Sensor sensor : list){
            for (Coord coord : sensor.signalRangePerimeter(maxCoord, maxCoord)) {

                boolean found = list.stream().noneMatch(otherSensor -> otherSensor.isInSignalRange(coord));

                if (found) {
                    beaconCoord = coord;
                    break outerLoop;
                }
            }
        }

        assertNotNull(beaconCoord);
        assertEquals(new Coord(14L,11L), beaconCoord);
        assertEquals(56000011, beaconCoord.getX() * 4000000 + beaconCoord.getY());
    }

    @Test
    void testActualTuningFrequency() throws IOException {
        File input = new ClassPathResource("2022/BeaconInput.txt").getFile();
        List<Sensor> list = new SensorFactory().generateFromFile(input);

        long maxCoord = 4000000;

        Coord beaconCoord = null;
        outerLoop: for(Sensor sensor : list){
            for (Coord coord : sensor.signalRangePerimeter(maxCoord, maxCoord)) {

                    boolean found = list.stream().noneMatch(otherSensor -> otherSensor.isInSignalRange(coord));

                    if (found) {
                        beaconCoord = coord;
                        break outerLoop;
                    }
            }
        }

        assertNotNull(beaconCoord);
        assertEquals(new Coord(3204400L,3219131L), beaconCoord);
        assertEquals(12817603219131L, beaconCoord.getX() * 4000000 + beaconCoord.getY());
    }
}
