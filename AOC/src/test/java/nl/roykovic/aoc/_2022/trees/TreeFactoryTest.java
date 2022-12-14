package nl.roykovic.aoc._2022.trees;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeFactoryTest {
    @Test
    void testAmountOfVisibleExampleTrees() throws IOException {
        File input = new File("src/test/resources/2022/TreeExampleInput.txt");
        TreePatch trees = new TreeFactory().generateFromFile(input);

        Tree[] visibleTrees = Arrays.stream(trees.patch()).flatMap(Arrays::stream).filter(Tree::isVisible).toArray(Tree[]::new);

        assertEquals(21, visibleTrees.length);

    }

    @Test
    void testAmountOfVisibleActualTrees() throws IOException {
        File input = new ClassPathResource("2022/TreesInput.txt").getFile();
        TreePatch trees = new TreeFactory().generateFromFile(input);

        Tree[] visibleTrees = Arrays.stream(trees.patch()).flatMap(Arrays::stream).filter(Tree::isVisible).toArray(Tree[]::new);

        assertEquals(1719, visibleTrees.length);

    }

    @Test
    void testHighestScenicExampleScore() throws IOException {
        File input = new File("src/test/resources/2022/TreeExampleInput.txt");
        TreePatch trees = new TreeFactory().generateFromFile(input);

        int highestScenicScore = Arrays.stream(trees.patch()).flatMap(Arrays::stream).mapToInt(Tree::getScenicScore).max().orElse(0);
        assertEquals(8, highestScenicScore);
    }

    @Test
    void testHighestScenicActualScore() throws IOException {
        File input = new ClassPathResource("2022/TreesInput.txt").getFile();
        TreePatch trees = new TreeFactory().generateFromFile(input);

        int highestScenicScore = Arrays.stream(trees.patch()).flatMap(Arrays::stream).mapToInt(Tree::getScenicScore).max().orElse(0);
        assertEquals(590824, highestScenicScore);
    }
}
