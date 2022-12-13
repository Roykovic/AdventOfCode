package nl.roykovic.aoc.hillclimb;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

        List<Node> lowestNodes = list.stream().filter(it -> it.getElevation() == 0).toList();   //get all nodes with lowest elevation

        List<Integer> shortestPaths = new ArrayList<>();

        for(Node lowNode : lowestNodes){
            shortestPaths.add(lowNode.getShortestPath().size());

        }

        assertEquals(29, shortestPaths.stream().filter(it -> it != 0).sorted().findFirst().orElse(null)); //get the lowest (non 0) value from shortestpaths
    }

    @Test
    void testActualShortestPathFromLowestPoints() throws IOException {
        File input = new ClassPathResource("HillClimbInput.txt").getFile();
        List<Node> list = new NodeFactory().generateFromFile(input, true);

        Node endNode = list.stream().filter(Node::isEnd).findFirst().orElseThrow(IllegalArgumentException::new);

        DijkstraService.calculateShortestPathFromSource(endNode);

        List<Node> lowestNodes = list.stream().filter(it -> it.getElevation() == 0).toList();   //get all nodes with lowest elevation

        List<Integer> shortestPaths = new ArrayList<>();

        for(Node lowNode : lowestNodes){
            shortestPaths.add(lowNode.getShortestPath().size());

        }

        assertEquals(459, shortestPaths.stream().filter(it -> it != 0).sorted().findFirst().orElse(null)); //get the lowest (non 0) value from shortestpaths
    }
}
