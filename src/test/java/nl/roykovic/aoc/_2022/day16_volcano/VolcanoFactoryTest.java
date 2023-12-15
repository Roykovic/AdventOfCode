package nl.roykovic.aoc._2022.day16_volcano;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.IOException;

public class VolcanoFactoryTest {
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "MonkeyTestInput.txt,true,10605",
            "MonkeyInput.txt,false,58794"
    })
    void testPressureReleased(String filename, boolean test, long expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        Volcano volcano = new VolcanoFactory().generateFromFile(input);

//        System.out.println(volcano.getValves().get(0).getMostOptimalValveFromHere(30, 0).getValue());
    }
}
