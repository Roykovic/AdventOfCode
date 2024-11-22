package nl.roykovic.aoc._2016.day15_bouncyballs;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BouncyBallsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "BouncyBallsTestInput.txt,true,false,5",
            "BouncyBallsInput.txt,false,false,148737",
            "BouncyBallsInput.txt,false,true,2353212",
    })
    public void test(String filename, boolean test, boolean addDisk, int expected) {
        var input = FileReaderService.streamLinesFromFile(2016, filename, test);
        var discs = new ArrayList<>(new BouncyBallsFactory().generate(input));

        if(addDisk){
            discs.add(new Disc(11, 0));
        }

        int index = 0;
        while(true){
            var time = index;
            var everythingOpen = true;
            for(Disc d : discs){
                time++;
                if(!d.isOpen(time)){
                    everythingOpen = false;
                }
            }
            if(everythingOpen){
                break;
            }
            index++;
        }

        assertEquals(expected, index);
    }
}
