package nl.roykovic.aoc._2022.day8_trees;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "TreeExampleInput.txt,true,21",
            "TreesInput.txt,false,1719"
    })
    void testAmountOfVisibleTrees(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        TreePatch trees = new TreeFactory().generateFromFile(input);

        Tree[] visibleTrees = Arrays.stream(trees.patch()).flatMap(Arrays::stream).filter(Tree::isVisible).toArray(Tree[]::new);

        assertEquals(expected, visibleTrees.length);

    }

    @ParameterizedTest
    @CsvSource({
            "TreeExampleInput.txt,true,8",
            "TreesInput.txt,false,590824"
    })
    void testHighestScenicScore(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        TreePatch trees = new TreeFactory().generateFromFile(input);

        int highestScenicScore = Arrays.stream(trees.patch()).flatMap(Arrays::stream).mapToInt(Tree::getScenicScore).max().orElse(0);
        assertEquals(expected, highestScenicScore);
    }
}
