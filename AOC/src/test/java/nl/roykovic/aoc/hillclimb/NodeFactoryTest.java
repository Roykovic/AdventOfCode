package nl.roykovic.aoc.hillclimb;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeFactoryTest {

    @Test
    void testExampleShortestPath() throws IOException {
        File input = new File("src/test/resources/HillClimbTestInput.txt");
        List<Node> list = new NodeFactory().generateFromFile(input, false);

        Node startNode = list.stream().filter(Node::isStart).findFirst().orElseThrow(IllegalArgumentException::new);
        Node endNode = list.stream().filter(Node::isEnd).findFirst().orElseThrow(IllegalArgumentException::new);

        DijkstraService.calculateShortestPathFromSource(startNode);

        assertEquals(31, endNode.getShortestPath().size());
    }

    @Test
    void testActualShortestPath() throws IOException {
        File input = new ClassPathResource("HillClimbInput.txt").getFile();
        List<Node> list = new NodeFactory().generateFromFile(input, false);

        Node startNode = list.stream().filter(Node::isStart).findFirst().orElseThrow(IllegalArgumentException::new);
        Node endNode = list.stream().filter(Node::isEnd).findFirst().orElseThrow(IllegalArgumentException::new);

        DijkstraService.calculateShortestPathFromSource(startNode);

        assertEquals(468, endNode.getShortestPath().size());
    }

    @Test
    void testExampleShortestPathFromLowestPoints() throws IOException {
        File input = new File("src/test/resources/HillClimbTestInput.txt");
        List<Node> list = new NodeFactory().generateFromFile(input, true);

        Node endNode = list.stream().filter(Node::isEnd).findFirst().orElseThrow(IllegalArgumentException::new);

        DijkstraService.calculateShortestPathFromSource(endNode);

        assertEquals(29, list.stream().filter(it -> it.getElevation() == 0).mapToInt(it -> it.getShortestPath().size()).filter(it -> it > 0).sorted().findFirst().orElse(0)); //get the lowest (non 0) value from the shortestpaths

    }

    @Test
    void testActualShortestPathFromLowestPoints() throws IOException {
        File input = new ClassPathResource("HillClimbInput.txt").getFile();
        List<Node> list = new NodeFactory().generateFromFile(input, true);

        Node endNode = list.stream().filter(Node::isEnd).findFirst().orElseThrow(IllegalArgumentException::new);

        DijkstraService.calculateShortestPathFromSource(endNode);

        assertEquals(459, list.stream().filter(it -> it.getElevation() == 0).mapToInt(it -> it.getShortestPath().size()).filter(it -> it > 0).sorted().findFirst().orElse(0)); //get the lowest (non 0) value from the shortestpaths

    }
}
