package nl.roykovic.aoc._2015.day15_ingredients;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class IngredientsFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "IngredientsTestInput.txt,true,62842880",
            "IngredientsInput.txt,false,18965440",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);
        var output = new IngredientsFactory().generate(input);

        int max = 0;
        int teaspoons = 100;

        List<int[]> combinations = findAllCombinations(teaspoons, output.size());

        for(var combination : combinations){

            int capacity =0;
            int durability =0;
            int flavor = 0;
            int texture =0;

            for(int i = 0; i< combination.length; i++){
                capacity += combination[i] * output.get(i).getCapacity();
                durability += combination[i] * output.get(i).getDurability();
                flavor += combination[i] * output.get(i).getFlavor();
                texture += combination[i] * output.get(i).getTexture();
            }

            int result = Integer.max(0,capacity) * Integer.max(0,durability) *  Integer.max(0,flavor) *  Integer.max(0,texture);

            if(result > max){
                max = result;
            }
        }

        assertEquals(expected, max);
    }

    private List<int[]> findAllCombinations(int target, int numbers) {
        List<int[]> results = new ArrayList<>();
        generateCombinations(results, new int[numbers], target, 0);
        return results;
    }

    private void generateCombinations(List<int[]> results, int[] combination, int target, int index) {
        if (index == combination.length - 1) {
            combination[index] = target;
            results.add(combination.clone());
            return;
        }

        for (int i = 1; i <= target; i++) {
            combination[index] = i;
            generateCombinations(results, combination, target - i, index + 1);
        }
    }
}
