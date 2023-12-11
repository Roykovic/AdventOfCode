package nl.roykovic.aoc._2023.day8_maps;

import nl.roykovic.aoc.utils.FileReaderService;
import nl.roykovic.aoc.utils.Utils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "MapsTestInputOne.txt,true,2",
            "MapsTestInputTwo.txt,true,6",
            "MapsInput.txt,false,20513"
    })
    public void testRouteToEnd(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        String directions = input.get(0);

        var nodes = new MapsFactory().generate(input.stream().skip(2));

        String startNode = "AAA";
        String endNode = "ZZZ";
        String currentNode = startNode;

        int step = 0;
        while(!Objects.equals(currentNode, endNode)){
            char currentDirection = directions.charAt(step % directions.length());
            if(currentDirection == 'L'){
                currentNode = nodes.get(currentNode).left();
            }
            else{
                currentNode = nodes.get(currentNode).right();
            }
            step++;
        }

        assertEquals(expected, step);
    }

    @ParameterizedTest
    @CsvSource({
            "MapsTestInputPartTwo.txt,true,6",
            "MapsInput.txt,false,15995167053923"
    })
    @Disabled
    public void testRouteToMultipleEnds(String filename, boolean test, long expected){
        var input = FileReaderService.getLinesFromFile(2023, filename, test);
        String directions = input.get(0);

        var nodes = new MapsFactory().generate(input.stream().skip(2));

        List<String> currentNodes = nodes.keySet().stream().filter(it1 -> it1.endsWith("A")).toList();

        long currentLCM = 1L;

        for(String currentNode : currentNodes){
            int step = 0;
            while(!currentNode.endsWith("Z")){
                char currentDirection = directions.charAt(step % directions.length());
                if(currentDirection == 'L'){
                    currentNode = nodes.get(currentNode).left();
                }
                else{
                    currentNode = nodes.get(currentNode).right();
                }
                step++;
            }

            //Apparently LCM works, although it shouldnt.
            currentLCM = Utils.lcm(Long.valueOf(step), currentLCM);
        }

        assertEquals(expected, currentLCM);
    }
}
