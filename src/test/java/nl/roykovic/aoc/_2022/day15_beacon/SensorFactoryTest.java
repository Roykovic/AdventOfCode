package nl.roykovic.aoc._2022.day15_beacon;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SensorFactoryTest {
    @SuppressWarnings("NewObjectEquality")
    @ParameterizedTest
    @CsvSource({
            "BeaconTestInput.txt,true,10,26",
            "BeaconInput.txt,false,2000000,5181556"
    })
    void testLineNotBeaconIndices(String filename, boolean test, long yToCheck, long expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        List<Sensor> list = new SensorFactory().generateFromFile(input);

        HashSet<Coord> notBeaconIndicesOnY = new HashSet<>();

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

        assertEquals(expected, notBeaconIndicesOnY.stream().distinct().count());

    }

    @ParameterizedTest
    @CsvSource({
            "BeaconTestInput.txt,true,20,56000011",
            "BeaconInput.txt,false,4000000,12817603219131"
    })
    void testTuningFrequency(String filename, boolean test, long maxCoord, long expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        List<Sensor> list = new SensorFactory().generateFromFile(input);

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
        assertEquals(expected, beaconCoord.getX() * 4000000 + beaconCoord.getY());
    }
}
