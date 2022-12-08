package nl.roykovic.aoc.trees;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TreeFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("TreesInput.txt").getFile();
        TreePatch trees = new TreeFactory().generateFromFile(input);

        Tree[] visibleTrees = Arrays.stream(trees.getPatch()).flatMap(Arrays::stream).filter(Tree::isVisible).toArray(Tree[]::new);

        System.out.println(visibleTrees.length);

    }

    @Test
    void testFactory2() throws IOException {
        File input = new ClassPathResource("TreesInput.txt").getFile();
        TreePatch trees = new TreeFactory().generateFromFile(input);

        int highestScenicScore = Arrays.stream(trees.getPatch()).flatMap(Arrays::stream).mapToInt(Tree::getScenicScore).max().orElse(0);
        System.out.println(highestScenicScore);

    }
}
