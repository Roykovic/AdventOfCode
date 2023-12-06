package nl.roykovic.aoc._2023.day5_garden;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GardenFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "GardenTestinput.txt,true,35",
            "GardenInput.txt,false,600279879"
    })
    public void testGardenClosesLocation(String filename, boolean test, int expected){
        var input = FileReaderService.getFileAsString(2023, filename, test);

        String[] result = input.split("\\r\\n[\\r\\n]+");
        String[] seedStrings = result[0].trim().split(" ");
        String[] seeds = Arrays.copyOfRange(seedStrings, 1, seedStrings.length);

        Map<String, RangeMap> maps = new GardenFactory().generate(input);

        List<Long> dests = new ArrayList<>();
        for(String seedString : seeds){
            Long seed = Long.parseLong(seedString);
            Long soil = maps.get("seed-to-soil map").get(seed);
            Long fertilizer = maps.get("soil-to-fertilizer map").get(soil);
            Long water = maps.get("fertilizer-to-water map").get(fertilizer);
            Long light = maps.get("water-to-light map").get(water);
            Long temperature = maps.get("light-to-temperature map").get(light);
            Long humidity = maps.get("temperature-to-humidity map").get(temperature);
            Long location = maps.get("humidity-to-location map").get(humidity);
            dests.add(location);
        }

        assertEquals(expected, Collections.min(dests));
    }

    @ParameterizedTest
    @CsvSource({
            "GardenTestinput.txt,true,35",
            "GardenInput.txt,false,600279879"
    })
    public void testGardenClosesLocationFromRange(String filename, boolean test, int expected){
        var input = FileReaderService.getFileAsString(2023, filename, test);

        String[] result = input.split("\\r\\n[\\r\\n]+");
        String[] seedStrings = result[0].trim().split(" ");
        String[] seeds = Arrays.copyOfRange(seedStrings, 1, seedStrings.length);

        List<Range> seedRanges = new ArrayList<>();

        for(int i = 0; i < seeds.length; i++){
            if(i % 2 != 0){
                long start = Long.parseLong(seeds[i-1]);
                long end = Long.parseLong(seeds[i])+start;

                seedRanges.add(new Range(start, end));
            }
        }

        Map<String, RangeMap> maps = new GardenFactory().generate(input);

        List<Range> dests = new ArrayList<>();
        for(Range seedRange : seedRanges){
            dests.addAll(maps.get("seed-to-soil map").getMappedRanges(seedRange).stream()
                    .map(it -> maps.get("seed-to-soil map").getMappedRanges(it))
                    .flatMap(Collection::stream)
                    .map(it -> maps.get("soil-to-fertilizer map").getMappedRanges(it))
                    .flatMap(Collection::stream)
                    .map(it -> maps.get("fertilizer-to-water map").getMappedRanges(it))
                    .flatMap(Collection::stream)
                    .map(it -> maps.get("water-to-light map").getMappedRanges(it))
                    .flatMap(Collection::stream)
                    .map(it -> maps.get("light-to-temperature map").getMappedRanges(it))
                    .flatMap(Collection::stream)
                    .map(it -> maps.get("temperature-to-humidity map").getMappedRanges(it))
                    .flatMap(Collection::stream)
                    .map(it -> maps.get("humidity-to-location map").getMappedRanges(it))
                    .flatMap(Collection::stream)
                    .toList());
        }
        assertEquals(expected, dests.stream().mapToLong(Range::getStart).min().orElseThrow());
    }
}
