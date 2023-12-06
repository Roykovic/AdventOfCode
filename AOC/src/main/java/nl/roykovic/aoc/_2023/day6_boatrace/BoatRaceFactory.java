package nl.roykovic.aoc._2023.day6_boatrace;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BoatRaceFactory {

    public int generate(Stream<String> input){

        List<BoatRace> races = new ArrayList<>();

        var numbers = input
                .map(it -> it.split(":")[1])
                .map(it -> it.trim().split(" "))
                .map(it -> Arrays.stream(it).filter(StringUtils::isNotBlank).map(Integer::parseInt).toList())
                .toList();

        var times = numbers.get(0);
        var distances = numbers.get(1);

        for(int i = 0; i< times.size(); i++){
            races.add(i, new BoatRace(times.get(i), distances.get(i)));
        }

        return races.stream().map(BoatRace::getNumberOfPossibilities).reduce((a, b) -> a*b).orElseThrow(RuntimeException::new);
    }

    private class BoatRace{
        private int time;
        private int distance;

        public BoatRace(int time, int distance) {
            this.time = time;
            this.distance = distance;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getNumberOfPossibilities(){
            int counter = 0;

            for(int i = 1; i <= time; i++){
                if((i * (time -i)) > distance){
                    counter++;
                }
            }
            return counter;
        }
    }
}
