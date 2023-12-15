package nl.roykovic.aoc._2022.day12_hillclimb;

import nl.roykovic.aoc.utils.DijkstraService;
import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "HillClimbTestInput.txt,true,31",
            "HillClimbInput.txt,false,468"
    })
    void testShortestPath(String filename, boolean test, long expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        List<Node> list = new NodeFactory().generateFromFile(input, false);

        Node startNode = list.stream().filter(Node::isStart).findFirst().orElseThrow(IllegalArgumentException::new);
        Node endNode = list.stream().filter(Node::isEnd).findFirst().orElseThrow(IllegalArgumentException::new);

        DijkstraService.calculateShortestPathFromSource(startNode);

        assertEquals(expected, endNode.getShortestPath().size());
    }

    @ParameterizedTest
    @CsvSource({
            "HillClimbTestInput.txt,true,29",
            "HillClimbInput.txt,false,459"
    })
    void testShortestPathFromLowestPoints(String filename, boolean test, long expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        List<Node> list = new NodeFactory().generateFromFile(input, true);

        Node endNode = list.stream().filter(Node::isEnd).findFirst().orElseThrow(IllegalArgumentException::new);

        DijkstraService.calculateShortestPathFromSource(endNode);

        assertEquals(expected, list.stream().filter(it -> it.getElevation() == 0).mapToInt(it -> it.getShortestPath().size()).filter(it -> it > 0).sorted().findFirst().orElseThrow(IllegalArgumentException::new)); //get the lowest (non 0) value from the paths from nodes with elevation == 0

    }
}
